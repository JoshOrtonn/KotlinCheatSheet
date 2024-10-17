package designPatterns.builderPattern.exampleCode

import designPatterns.builderPattern.exampleCode.Subscription.Frequency.DAILY
import designPatterns.builderPattern.exampleCode.Subscription.Frequency.IMMEDIATELY
import designPatterns.builderPattern.exampleCode.Subscription.Frequency.WEEKLY
import designPatterns.builderPattern.exampleCode.Subscription.Topic.ANALYTICS
import designPatterns.builderPattern.exampleCode.Subscription.Topic.NEWS
import designPatterns.builderPattern.exampleCode.Subscription.Topic.SECURITY_ALERTS


// Representing subscription of topics with frequency
data class NotificationSettings(val enabled: Boolean, val subscriptions: List<Subscription>)

data class Subscription(val destination: Destination, val topic: Topic, val frequency: Frequency) {
    enum class Topic { NEWS, ANALYTICS, SECURITY_ALERTS}
    enum class Frequency { IMMEDIATELY, DAILY, WEEKLY }
}

// Where notification type will go
sealed interface Destination
@JvmInline value class EmailAddress(val value: String) : Destination
@JvmInline value class PhoneNumber(val value: String) : Destination

fun main() {
    createNotificationSettings()
}

fun createNotificationSettings(phoneNumber: PhoneNumber? = null, emailAddress: EmailAddress? = null): NotificationSettings {
    // If either are null, we'd need to lift the subscriptions list out of the constructor
    // And compose them before hand.
    // Could get messy.
    return NotificationSettings(
        enabled = true,
        subscriptions = listOf(
            Subscription(EmailAddress("example@example.com"), ANALYTICS, DAILY),
            Subscription(EmailAddress("example@example.com"), NEWS, WEEKLY),
            Subscription(PhoneNumber("1-555-555-5555"), SECURITY_ALERTS, IMMEDIATELY)
        )
    )
}