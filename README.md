# <img style="height: 3vh; width: auto;" src="https://www.spigotmc.org/data/resource_icons/112/112272.jpg?1692973102"> VortexSidebars
> Customizable sidebars for every purpose
## About
VortexSidebars was made to make it easy for anyone to make sidebars for anything.

It supports hex codes, animated text, fields, and more!

## Example
Code:

<img src="https://proxy.spigotmc.org/c70503aee4842affdacddbe78aa89eacbc8f7d58?url=https%3A%2F%2Fmedia.discordapp.net%2Fattachments%2F909603146001682493%2F1144635557104853002%2Fexample_code.png%3Fwidth%3D1416%26height%3D754">

Result (click):

[![Demo](http://img.youtube.com/vi/BlrlQICapT0/0.jpg)](http://www.youtube.com/watch?v=BlrlQICapT0 "VortexSidebars Demo")

## Usage

[JavaDocs](http://adfoc.us/82454497461528)

### Installation
<details>
    <summary>Maven</summary>

```xml
<project>

    <repositories>
        <repository>
            <id>codemc-releases</id>
            <url>https://repo.codemc.io/repository/maven-releases/</url>
        </repository>
    </repositories>
    
    <dependencies>
        <dependency>
            <groupId>us.teaminceptus</groupId>
            <artifactId>vortexsidebars</artifactId>
            <version>[VERSION]</version>
        </dependency>
    </dependencies>
    
</project>
```
</details>
<details>
    <summary>Gradle (Groovy)</summary>

```gradle
repositories {
    maven { url 'https://repo.codemc.io/repository/maven-snapshots/' }
}

dependencies {
    implementation 'us.teaminceptus:vortexsidebars:[VERSION]'
}
```
</details>

<details>
    <summary>Gradle (Kotlin DSL)</summary>

```kotlin
repositories {
    maven(url = "https://repo.codemc.io/repository/maven-snapshots/")
}

dependencies {
    implementation('us.teaminceptus:vortexsidebars:[VERSION]')
}
```
</details>
