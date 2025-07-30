# Tasks Demo

This is a minimalist reproducible example of using `AuthorizationClient` to access Google Tasks API. 

Result: 

1. It works on emulators
2. It does not work on real device

## Setup - Android

Code was copy-pasted from the official documentation here:

https://developer.android.com/identity/authorization

Only thing I've changed were requested Scopes (instead of `Drive` scope I uses `Google Tasks` scopes):

* "https://www.googleapis.com/auth/tasks",
* "https://www.googleapis.com/auth/tasks.readonly"

You can find the [relevant code here.](https://github.com/mil84/TasksDemo/blob/master/app/src/main/java/sk/tasks/pokus/AuthClient.kt)

## Setup - Cloud console

I went step by step according to docs and set my cloud console like this:

1. I created a new project: https://console.cloud.google.com/projectcreate?
2. I enabled Tasks API for my new project: https://console.cloud.google.com/apis/enableflow?apiid=tasks.googleapis.com
3. I created a new brand: https://console.cloud.google.com/auth/branding
4. I created an OAuth client: https://console.cloud.google.com/auth/clients
   * type `Android`, package of my app, SHA-1 (double checked)
   * I also tried type `WEB`
   * I also experimented with activating `Custom URI scheme` in `Advanced Settings`, doesn't seem to make much difference
5. I set-up all requested scopes: https://console.cloud.google.com/auth/scopes
6. Finally, I added also tester emails (my account): https://console.cloud.google.com/auth/audience

Regardless, I still get errors mentioned in [section Errors](##Errors) below. 

> **NOTE1:** When I request an access to different, non-restricted or non-sensitive scopes, `AuthorizationClient` works fine. So cloud console setup is likely correct, and mistake is probably on the client side (android app).
>
> **NOTE2:** Also, the very same cloud configucarion works if I'm using `AppAuth` library in custom tabs/browser to authorize my app - that also indicates error is on client side (android app) - or in `AuthorizationClient`?

## Errors

After clicking on Authorize button, I get these screens in this order:

![1|200x100](/screenshots/step_1.png)
![2](/screenshots/step_2.png)
![3](/screenshots/step_3.png)
![4](/screenshots/step_4.png)

There are no errors in the logcat, respectively only this error after I (inevitably...) cancel the dialog, but that's expected:

```
ERR: 16: [16] Cancelled by user., null, 16: [16] Cancelled by user., ALL: com.google.android.gms.common.api.ApiException: 16: [16] Cancelled by user.
```

## Notes
