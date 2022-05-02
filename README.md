# Reactive MVVM with Flows 📱

A showcase app to demonstrate how a **reactive MVVM** approach using **Flows** could look in Android.


## Android development

This MVVM implementation orchestrates the whole app functionality through the next Flows:

* State = [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#stateflow)
* Events = [Channel](https://kotlinlang.org/docs/channels.html)
* Actions = Not using flows anymore. However, if needed, a [SharedFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#sharedflow) could be used for this use case.

The app follows a Unidirectional Data Flow pattern (UDF) like MVI but leaves out complexities such as reducers or processors.

<img src="mvvm_diagram.png" alt="MVVM UDF" width="600">

## The app - Your Daily Quote 💬

The app is a simple **random Quote generator** with the ability to share the generated Quote.

<img src="screenshot.png" alt="Your Daily Quote app" width="250">


## Third party data

The quotes used throughout the app have been picked from the open source project [Quotable](https://github.com/lukePeavey/quotable).

