# Cordova Screen Locker Plugin
The plugin helps you to lock and unlock device screen programmatically.

#Supported Platforms

The plugin version 0.2.0 supports only 
Android (SDK >=7)

Other platforms will be added in new versions of plugin

#Installation

cordova plugin add https://github.com/kitolog/cordova-plugin-screen-locker.git

#Sample

const success = () => { console.log('screen unlock success'); };
const error = (e) => { console.log('screen unlock error', e); };

window.screenLocker.unlock(success, error, 10);

#License

This software is released under the Apache 2.0 License.

© 2015 AppLurk, All rights reserved
