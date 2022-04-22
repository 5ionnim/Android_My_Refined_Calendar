![initial](https://user-images.githubusercontent.com/68628681/164603634-9ab51556-1775-4969-bb6f-8ee27ee357d3.png)
# Android_My_Refined_Calendar
## Installation
Step 1. Add it in your root build.gradle at the end of repositories

```groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
Step 2. Add the dependency

```groovy
dependencies {
  implementation 'com.github.5ionnim:Android_My_Refined_Calendar:${version}'
}
```
## Usage
Step 1. Add `CalendarPager` into your layouts or view hierarchy.

like this. (you can set minimum year and maximum year by adding 'minYear' and 'maxYear' attributions)

```xml
<sionnim.android.refinedcalendar.CalendarPager
        android:id="@+id/calendarPager"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:minYear="1995"
        app:maxYear="2030"/>
```

Step 2. Set a `CalendarPager.setOnPageSelectedListener` and `CalendarPager.setOnItemClickListener` when you need it.

Or if you need to move page of CalendarPager you can call `CalendarPager.moveCalendarPage()`.
you can see the example [here]

[here]: /app/src/main/java/sionnim/android/myrefinedcalendar/MainActivity.java

## License

it's specified in the [LICENSE] file.

[LICENSE]: /LICENSE
