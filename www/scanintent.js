/**
 * cordova Web Intent plugin
 * Copyright (c) Boris Smus 2010
 *
 */
(function (cordova) {
    var ScanIntent = function (params, success, fail) {
        return cordova.exec(function (args) {
            success(args);
        }, function (args) {
            fail(args);
        }, 'ScanIntent', 'scan', [params]);
    };

    window.ScanIntent = ScanIntent;

    // backwards compatibility
    window.plugins = window.plugins || {};
    window.plugins.scanintent = window.ScanIntent;
})(window.PhoneGap || window.Cordova || window.cordova);
