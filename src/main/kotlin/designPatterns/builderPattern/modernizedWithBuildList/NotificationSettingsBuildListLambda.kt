package designPatterns.builderPattern.modernizedWithBuildList

import designPatterns.builderPattern.modernizedWithBuildList.Subscription.Frequency.DAILY
import designPatterns.builderPattern.modernizedWithBuildList.Subscription.Frequency.IMMEDIATELY
import designPatterns.builderPattern.modernizedWithBuildList.Subscription.Frequency.WEEKLY
import designPatterns.builderPattern.modernizedWithBuildList.Subscription.Topic.ANALYTICS
import designPatterns.builderPattern.modernizedWithBuildList.Subscription.Topic.NEWS
import designPatterns.builderPattern.modernizedWithBuildList.Subscription.Topic.SECURITY_ALERTS


// Representing subscription of topics with frequency
data class NotificationSettings(val enabled: Boolean, val subscriptions: List<Subscription>)

data class Subscription(val destination: Destination, val topic: Topic, val frequency: Frequency) {
    enum class Topic { NEWS, ANALYTICS, SECURITY_ALERTS }
    enum class Frequency { IMMEDIATELY, DAILY, WEEKLY }
}

// Where notification type will go
sealed interface Destination
@JvmInline
value class EmailAddress(val value: String) : Destination
@JvmInline
value class PhoneNumber(val value: String) : Destination

fun main() {
    createNotificationSettings()
}

fun createNotificationSettings(
    phoneNumber: PhoneNumber? = null,
    emailAddress: EmailAddress? = null
): NotificationSettings {
    return NotificationSettings(
        enabled = true,
        // For simple constructors this is slimline enough
        // But for complex objects
        // with much logic the builder pattern would be more useful
        subscriptions = buildList {
            if (emailAddress != null) {
                add(Subscription(EmailAddress("example@example.com"), ANALYTICS, DAILY))
                add(Subscription(EmailAddress("example@example.com"), NEWS, WEEKLY))
            }
            if (phoneNumber != null) add(Subscription(PhoneNumber("1-555-555-5555"), SECURITY_ALERTS, IMMEDIATELY))
        }
    )
}