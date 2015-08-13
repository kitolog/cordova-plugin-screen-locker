var exec = require('cordova/exec');

var screenLocker = {
    unlock: function(successCallback, errorCallback, timeout) {
        exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'ScreenLocker', // mapped to our native Java class called "ScreenLocker"
            'unlock', // with this action name
            [{                  // and this array of custom arguments to create our entry
                "timeout": timeout
            }]
        );
     }
}

module.exports = screenLocker;