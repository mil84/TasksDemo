# Tasks Demo

This is a minimalist reproducible example of using `AuthorizationClient` to access Google Tasks API. 

## Setup - Android

Code was copy-pasted from the official documentation here:

https://developer.android.com/identity/authorization

Only thing I've changed were requested Scopes (instead of `Drive` scope I uses `Google Tasks` scopes):

"https://www.googleapis.com/auth/tasks",
"https://www.googleapis.com/auth/tasks.readonly"

You can find the [relevant code here.](https://github.com/mil84/TasksDemo/blob/master/app/src/main/java/sk/tasks/demo/AuthClient.kt)

## Setup - Cloud console

I went step by step according to docs and set my cloud console like this:

1. I created a new project: https://console.cloud.google.com/projectcreate?
2. I enabled Tasks API for my new project: https://console.cloud.google.com/apis/enableflow?apiid=tasks.googleapis.com
3. I created a new brand: https://console.cloud.google.com/auth/branding
4. I created an OAuth client: https://console.cloud.google.com/auth/clients
   a. package of my app: my app
   b. SHA-1: from debug key (double checked)
   NOTE: I also experimented with activating `Custom URI scheme` in `Advanced Settings`, but it had no effect on Android client...
5. I set-up all requested scopes: https://console.cloud.google.com/auth/scopes
6. Finally, I added also tester emails (my account): https://console.cloud.google.com/auth/audience

Regardless all of these issues, I still get errors mentioned in [section Errors](##Errors) below. 

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
