# Compose QR Code Generator
Introducing QR Code Generator, a cutting-edge development tool built on Jetpack Compose. This innovative framework simplifies QR code generation by providing a seamless andefficient solution to transform string values into QR codes.
With a focus on simplicity and performance, QR Code Generator empowers developers to effortlessly integrate QR code functionality into their Jetpack Compose projects, opening new possibilities for dynamic content sharing and data encoding. 
Also, This library provides a functionality that you can change the qr code colors.

## Tech Stack
* Kotlin
* Jetpack Compose

## Usage
<img width="473" alt="Screenshot 2024-01-10 at 13 01 02" src="https://github.com/mertkalecik/QRGenerator/assets/38656031/820bd1c5-72ef-4281-9fb7-4ab9f9bac69f">

## Sample Application:
<img src="https://github.com/mertkalecik/QRGenerator/assets/38656031/cf3307f6-962d-43ef-8d0b-d149c9b2a1dd" width="480" height="1024">

## Implementation
Step 1. Add it in your root build.gradle at the end of repositories:

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency

```
dependencies {
    implementation 'com.github.mertkalecik:QRGenerator:1.0.2'
}
```


