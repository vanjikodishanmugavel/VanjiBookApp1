# Android Book App - Complete Setup Guide

## Files Created

### Java Files (place in `java/com/example/vanjibookapp/`)
1. **Book.java** - Parcelable Book object for passing between activities
2. **MainActivity.java** - First activity to collect book data
3. **SecondActivity.java** - Second activity to display book details and take photo
4. **ThirdActivity.java** - Third activity with two common intents

### Layout Files (place in `res/layout/`)
1. **activity_main.xml** - Layout for first activity
2. **activity_second.xml** - Layout for second activity
3. **activity_third.xml** - Layout for third activity

## AndroidManifest.xml Setup

Add these activities inside the `<application>` tag:

```xml
<activity 
    android:name=".MainActivity"
    android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>

<activity android:name=".SecondActivity" />
<activity android:name=".ThirdActivity" />
```

## App Flow

### Activity 1 (MainActivity)
- ✅ Collects book data: Title, Author, Year
- ✅ Passes Book object to Activity 2
- ✅ Saves state on rotation

### Activity 2 (SecondActivity)
- ✅ Displays book details passed from Activity 1
- ✅ Camera intent to take photo of book (displays photo, doesn't save)
- ✅ Next button to go to Activity 3
- ✅ Saves photo state on rotation

### Activity 3 (ThirdActivity)
- ✅ Phone Dialer Intent (ACTION_DIAL) - enter phone number, opens dialer
- ✅ Map/Location Intent (ACTION_VIEW) - enter location, opens map
- ✅ Simple TextField + Button UI (no email/web search)
- ✅ Saves state on rotation

## Features Implemented
- ✅ Three activities with proper flow
- ✅ Object passing between activities (Parcelable Book)
- ✅ Common implicit intents:
  - Camera (ACTION_IMAGE_CAPTURE)
  - Phone Dialer (ACTION_DIAL)
  - Maps (ACTION_VIEW with geo URI)
- ✅ State preservation on rotation for all activities
- ✅ Input validation with Toast messages
- ✅ Simple, clean UI design

## Testing the App
1. Launch app → Enter book details → Submit
2. View book details → Take photo → See photo displayed → Next
3. Enter phone number → Dial (opens dialer app)
4. Enter location → View on Map (opens maps app)
5. Rotate device on any screen → Data persists


