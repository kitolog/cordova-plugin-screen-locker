var exec = require('cordova/exec');

var screenLocker = {
    unlock: function(successCallback, errorCallback, timeout) {

        if(typeof timeout == 'undefined'){
            timeout = 0;
        }
        exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'ScreenLocker', // mapped to our native Java class called "ScreenLocker"
            'unlock', // with this action name
            [{                  // and this array of custom arguments to create our entry
                "timeout": timeout
            }]
        );
     },
     lock: function(successCallback, errorCallback, timeout) {

        if(typeof timeout == 'undefined'){
            timeout = 0;
        }

        exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'ScreenLocker', // mapped to our native Java class called "ScreenLocker"
            'lock', // with this action name
            [{                  // and this array of custom arguments to create our entry
                "timeout": timeout
            }]
        );
     }
}

module.exports = screenLocker;