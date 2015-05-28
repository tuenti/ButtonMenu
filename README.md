Button Menu [![Build Status](https://travis-ci.org/tuenti/ButtonMenu.svg?branch=master)](https://travis-ci.org/tuenti/ButtonMenu) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.tuenti.buttonmenu/library/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.tuenti.buttonmenu/library) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Button%20Menu-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/887)
===========

ButtonMenu is an Android library created to build user interfaces based on buttons. This library has been implemented
 using Model View ViewModel pattern combined with an Android custom view that extends LinearLayout.

In this library you will find a custom view implementation called ButtonMenu and a custom animator called
ScrollAnimator you can use to link the scroll of a ListView with your ButtonMenu to show or hide the view when the
user uses the ListView scroll.

This library works on Android 2.X or higher versions.

Screenshots
-----------

![Demo Screenshot 1][1]
![Demo Screenshot 2][2]

Download
--------

Download the project, compile it using ``mvn clean install`` import ``buttonmenu-1.0.9.jar`` into your project.

Or declare it into your pom.xml

```xml
<dependency>
    <groupId>com.tuenti.buttonmenu</groupId>
    <artifactId>library</artifactId>
    <version>1.0.9</version>
</dependency>
```


Or into your build.gradle
```groovy
dependencies{
    compile 'com.tuenti.buttonmenu:library:1.0.9'
}
```


Usage
-----

* 1. Add a ``ButtonMenu`` to your layout.

```xml
<com.tuenti.buttonmenu.ButtonMenu
		android:id="@+id/button_menu"
		style="@style/button_menu"/>
```

* 2. Initialize your ButtonMenu widget with a ButtonMenuVM implementation inside your Activity or Fragment. You can use
our SimpleButtonMenuVM implementation or create your own ButtonMenuVM implementation.

```java
private void initializeButtonMenu() {
	button_menu = (ButtonMenu) findViewById(R.id.button_menu);
	button_menu.setButtonMenuVM(buttonMenuVM);
	button_menu.initialize();
}
```

* 3. If you want to create your custom ButtonMenuVM implementation you can follow the sample implemented in
CustomButtonMenuVM.

```java
public class CustomButtonMenuVM extends SimpleButtonMenuVM {

/*
 * Every ButtonVM implementation could be moved to a different file extending SimpleButtonVM if needed.
 */
private final ButtonVM moment = new SimpleButtonVM(R.layout.moment_button, R.id.moment, null);
private final ButtonVM photo = new SimpleButtonVM(R.layout.photo_button, R.id.photo, null);
private final ButtonVM contact = new SimpleButtonVM(R.layout.contact_button, R.id.contact, null);

public CustomButtonMenuVM() {
	super();
	addItem(moment);
	addItem(photo);
	addItem(contact);
}
```

* 4. Connect your ButtonMenu widget with the ScrollAnimator to attach the scroll animation effect.

```java
private void initializeScrollAnimator() {
	ScrollAnimator scrollAnimator = new ScrollAnimator(button_menu, new ObjectAnimatorFactory());
	scrollAnimator.configureListView(lv_contacts);
	scrollAnimator.setDurationInMillis(300);
}
```

Review different ButtonVM implementations -like ``SimpleButtonVM`` or ``ButtonWithMutableSubjectVM`` - in this
project if you want to create your custom ButtonVM.

Credits & Contact
-----------------

ButtonMenu was created by [Android team at Tuenti Technologies S.L.](http://github.com/tuenti). You can follow Tuenti
engineering team on Twitter [@tuentieng](http://twitter.com/tuentieng).

License
-------

ButtonMenu is available under the Apache License, Version 2.0. See LICENSE.txt file for more info.

[1]: ./art/screenshot1.gif
[2]: ./art/screenshot2.gif
