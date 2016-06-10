# Cordova Screen Locker Plugin
The plugin helps you to lock and unlock device screen programmatically.

#Supported Platforms

The plugin version 0.2.1 supports only 
Android (SDK >=7)

Other platforms will be added in new versions of plugin

#Installation

```bash
cordova plugin add https://github.com/kitolog/cordova-plugin-screen-locker.git
```

#Sample

```javascript
var successCallback = function() {
  console.log('screen unlock success');
  // do some staff here
};

var errorCallback = function(e) {
  console.log('error: ' + e);
};

window.screenLocker.unlock(successCallback, errorCallback, 10);  // 10 seconds unlock timeout (third parameter is optional)
window.screenLocker.lock(successFun, errorFun);  // release screen unlock
```

#License

This software is released under the Apache 2.0 License.

© 2015 AppLurk, All rights reserved
