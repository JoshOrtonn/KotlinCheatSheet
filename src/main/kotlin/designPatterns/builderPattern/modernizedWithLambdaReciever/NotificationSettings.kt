package designPatterns.builderPattern.modernizedWithLambdaReciever

import designPatterns.builderPattern.modernizedWithLambdaReciever.Subscription.Topic
import designPatterns.builderPattern.modernizedWithLambdaReciever.Subscription.Frequency
import designPatterns.builderPattern.modernizedWithLambdaReciever.Subscription.Frequency.DAILY
import designPatterns.builderPattern.modernizedWithLambdaReciever.Subscription.Frequency.IMMEDIATELY
import designPatterns.builderPattern.modernizedWithLambdaReciever.Subscription.Frequency.WEEKLY
import designPatterns.builderPattern.modernizedWithLambdaReciever.Subscription.Topic.ANALYTICS
import designPatterns.builderPattern.modernizedWithLambdaReciever.Subscription.Topic.NEWS
import designPatterns.builderPattern.modernizedWithLambdaReciever.Subscription.Topic.SECURITY_ALERTS


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


// Instead of making them constructor properties, made them into properties on a builder
// Such that we can chain together multiple builders to then finally build all the components of the constructor
// Plus mutable enabled, and mutable list to be built.
class NotificationSettingsBuilder {
    var enabled: Boolean = false
    private val subscriptions = mutableListOf<Subscription>()

    //    fun addSubscription(dest: Destination, topic: Subscription.Topic, freq: Subscription.Frequency){
//        subscriptions.add(Subscription(dest, topic, freq))
//    }
    // Could even refactor the addSubscription so it becomes a bit more human readable
    // Through use of pair, and destructing argument, it can read better in the callsite
    fun send(topicToDestination: Pair<Topic, Destination>, frequency: Frequency) {
        val (topic, destination) = topicToDestination
        subscriptions.add(Subscription(destination, topic, frequency))
    }

    fun build(): NotificationSettings =
        NotificationSettings(enabled, subscriptions.toList())
}

// Function accepting lambda with a reciever
// Specifying the type to the left of the `.()`
// Instantiates the builder, applies the lambda to it.
// And returns build object
fun notificationSettings(block: NotificationSettingsBuilder.() -> Unit) =
    NotificationSettingsBuilder().apply(block).build()


fun main() {
    createNotificationSettings(null, PhoneNumber("0416377177"))
}

fun createNotificationSettings(emailAddress: EmailAddress?, phoneNumber: PhoneNumber?): NotificationSettings {
    // Consider it similar to with(NotificationSettingsBuilder)
    // As it's reference.
    // We are calling notificationSettings function with lambda as argument with builder as reference (with(builder))
    // Applying the same things, and then building it.
    return notificationSettings {
        enabled = true
        // Can even create a more expressive builder
        if (emailAddress != null) send(ANALYTICS to emailAddress, DAILY)
        if (emailAddress != null) send(NEWS to emailAddress, WEEKLY)
        if (phoneNumber != null) send(SECURITY_ALERTS to phoneNumber, IMMEDIATELY)
        if (phoneNumber != null) Topic.entries.forEach { send(it to phoneNumber, IMMEDIATELY) }
    }
}