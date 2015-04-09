# cordova-zxing-intent
Zxing Barcode Scan Intent for Cordova


Open a scan intent of Zxing from JS file.
I've created this plugin after creating a utility app that needed scan. Zxing cordova plugin did not scan most of the barcodes that Zxing offical android app scanned perfectly.

This plugin opens the Zxing Barcode Scanner app (if installed on device) and returns the scanned barcode.

## Adding the Plugin to your project ##

To install the plugin, use the Cordova CLI and enter the following:

`cordova plugin add https://github.com/IshaiJaffe/cordova-zxing-intent.git`

Or 

`cordova plugin add com.ishaijaffe.zxingintent`

## Using the plugin ##

The plugin creates the function `window.plugins.scanintent`:

`function(extras,success,fail)`

### Open the barcode scanner ###
`window.plugins.scanintent({},  function(result) { alert(result); },  function() {alert('Scan failed');});`


## Licence ##

The GNU License in LICENSE file


## Contributers ##

Uses Zxing Android Intent classes from https://github.com/zxing/zxing/tree/master/android-integration/src/main/java/com/google/zxing/integration/android
