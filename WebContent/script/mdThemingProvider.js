

 var fvrRedMap = $mdThemingProvider.extendPalette('red', {
    '500': '#CE1417',
    'contrastDefaultColor': 'light'
  });

  // Register the new color palette map with the name <code>neonRed</code>
  $mdThemingProvider.definePalette('fvrRed', fvrRedMap);

// Available palettes: red, pink, purple, deep-purple, indigo, blue, light-blue, cyan, teal, green, light-green, lime, yellow, amber, orange, deep-orange, brown, grey, blue-grey
$mdThemingProvider
	.theme('default')
	.primaryPalette('green',{'default': '500'})
	.accentPalette('fvrRed',{'default': '500'})
	.warnPalette('orange',{'default': '200'})
	.backgroundPalette('grey')
	;
