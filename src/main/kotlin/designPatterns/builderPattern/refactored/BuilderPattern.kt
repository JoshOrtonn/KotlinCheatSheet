package designPatterns.builderPattern.refactored

import designPatterns.builderPattern.refactored.Subscription.Frequency.DAILY
import designPatterns.builderPattern.refactored.Subscription.Frequency.IMMEDIATELY
import designPatterns.builderPattern.refactored.Subscription.Frequency.WEEKLY
import designPatterns.builderPattern.refactored.Subscription.Topic.ANALYTICS
import designPatterns.builderPattern.refactored.Subscription.Topic.NEWS
import designPatterns.builderPattern.refactored.Subscription.Topic.SECURITY_ALERTS


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

//Doesn't add much, but generally followed with this pattern so included it for thoroughness
interface INotificationSettingsBuilder {
    var enabled: Boolean
    fun addSubscription(dest: Destination, topic: Subscription.Topic, freq: Subscription.Frequency)
    fun build(): NotificationSettings
}

// Instead of making them constructor properties, made them into properties on a builder
// Such that we can chain together multiple builders to then finally build all the components of the constructor
// Plus mutable enabled, and mutable list to be built.
class NotificationSettingsBuilder: INotificationSettingsBuilder {
    override var enabled: Boolean = false
    private val subscriptions = mutableListOf<Subscription>()
    override fun addSubscription(dest: Destination, topic: Subscription.Topic, freq: Subscription.Frequency){
        subscriptions.add(Subscription(dest, topic, freq))
    }
    override fun build(): NotificationSettings = NotificationSettings(enabled, subscriptions.toList())
}


fun main() {
    createNotificationSettings(null, PhoneNumber("0416377177"))
}

fun createNotificationSettings(emailAddress: EmailAddress?, phoneNumber: PhoneNumber?): NotificationSettings {
    // Builder Pattern designed to give some flexibility when constructing a complex object
    // By allowing us to split up constructor across multiple statements.
    val builder = NotificationSettingsBuilder()
    builder.enabled = true
    // Can now conditionally compose constructor where previously it was not possible,
    // without creating list outside of constructor beforehand.
    // Using this pattern we can optionally chain each in a cleaner pattern.
    if(emailAddress != null) builder.addSubscription(emailAddress, ANALYTICS, DAILY)
    if(emailAddress != null) builder.addSubscription(emailAddress, NEWS, WEEKLY)
    if(phoneNumber != null) builder.addSubscription(phoneNumber, SECURITY_ALERTS, IMMEDIATELY)

    // could also easily add some computation if we wanted to subscribe to each topic
    if(phoneNumber != null) Subscription.Topic.entries.forEach { builder.addSubscription(phoneNumber, it, IMMEDIATELY) }
    return builder.build()

}