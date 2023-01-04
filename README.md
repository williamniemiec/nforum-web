![](https://raw.githubusercontent.com/williamniemiec/nforum-web/master/docs/images/logo/logo.jpg)

<h1 align='center'>nForum API</h1>
<p align='center'>Simple web forum built with servlets and JSP without Spring Framework.</p>
<p align="center">
	<a href="https://github.com/williamniemiec/nforum-web/actions/workflows/windows.yml"><img src="https://github.com/williamniemiec/nforum-web/actions/workflows/windows.yml/badge.svg" alt=""></a>
	<a href="https://github.com/williamniemiec/nforum-web/actions/workflows/macos.yml"><img src="https://github.com/williamniemiec/nforum-web/actions/workflows/macos.yml/badge.svg" alt=""></a>
	<a href="https://github.com/williamniemiec/nforum-web/actions/workflows/ubuntu.yml"><img src="https://github.com/williamniemiec/nforum-web/actions/workflows/ubuntu.yml/badge.svg" alt=""></a>
	<a href="http://java.oracle.com"><img src="https://img.shields.io/badge/java-8+-D0008F.svg" alt="Java compatibility"></a>
	<a href="https://github.com/williamniemiec/nforum-web/releases"><img src="https://img.shields.io/github/v/release/williamniemiec/nforum-web" alt="Release"></a>
	<a href="https://github.com/williamniemiec/nforum-web/blob/master/LICENSE"><img src="https://img.shields.io/github/license/williamniemiec/nforum-web" alt="License"></a>
</p>
<p align="center">
	<a href='https://wniemiec-web-nforum.up.railway.app/'><img alt='Deploy to Railway' src='https://railway.app/button.svg' width=200/></a>
</p>

<hr />

## ❇ Introduction
nForum is a simple web forum built with servlets and JSP pages along with [Selenium framework](https://www.selenium.dev/) for testing. This application was made for the sole purpose of learning how to develop a web application without using the Spring framework. You can interact with the project through the Heroku platform ([click here to access](https://wniemiec-web-nforum.up.railway.app/)).


### Login information
| Email| Password |
|------- | ----- |
| user@email.com |123|

## ⚠ Warnings
The hosting service Heroku may have a certain delay (~ 1 min) for uploading the application so the loading of the website may have a certain delay. 

## ✔ Requiremens
- [JDK 8+](https://www.oracle.com/java/);
- [Postgres v14+](https://www.postgresql.org/);
- [Chrome (for running tests)](https://www.google.com/chrome/);

## Commands

#### Install project dependencies

```
mvn install
```

#### Run project
```
mvn run
```


## 🖼 Gallery

![gif1](https://github.com/williamniemiec/nforum-web/blob/master/docs/gif/nforum-1.gif?raw=true)

![gif2](https://github.com/williamniemiec/nforum-web/blob/master/docs/gif/nforum-2.gif?raw=true)

![gif3](https://github.com/williamniemiec/nforum-web/blob/master/docs/gif/nforum-3.gif?raw=true)

## 🚩 Changelog
Details about each version are documented in the [releases section](https://github.com/williamniemiec/nforum-web/releases).

## 🗺 Project structure
![architecture](https://raw.githubusercontent.com/williamniemiec/nforum-web/master/docs/images/design/architecture.jpg)

#### Database
![database-diagram](https://raw.githubusercontent.com/williamniemiec/nforum-web/master/docs/images/design/db-schema.png?raw=true)


## 📁 Files

### /
|        Name        |Type|Description|
|----------------|-------------------------------|-----------------------------|
|docs |`Directory`|Documentation files|
|src  |`Directory`|Application and test files|

### /src
|        Name        |Type|Description|
|----------------|-------------------------------|-----------------------------|
|main|`Directory`|Application files|
|test|`Directory`|Test files|

### /src/main
|        Name        |Type|Description|
|----------------|-------------------------------|-----------------------------|
|java|`Directory`|Source files|
|webapp|`Directory`|JSP, CSS and JavaScript files|

### /src/main/java/wniemiec/web/nforum
|        Name        |Type|Description|
|----------------|-------------------------------|-----------------------------|
|config|`Directory`|Configuration classes|
|controllers|`Directory`|Classes that handle with HTTP requests and responses|
|dto|`Directory`|Data transfer object classes|
|repositories|`Directory`|Classes that handle with database|
|services|`Directory`|Classes responsible for providing data |
