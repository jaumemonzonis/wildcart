'use strict'

var trolleyes = angular.module('MyApp', [
    'ngRoute',
    'services',
    'components',
    'commonControllers',
    'tipousuarioControllers',
    'usuarioControllers',
    'tipoproductoControllers',
    'facturaControllers',
    'productoControllers',
    'lineaControllers'
]);


var moduleCommon = angular.module ('commonControllers',[]);
var moduleService = angular.module ('services',[]);
var moduleComponent = angular.module ('components',[]);
var moduleTipousuario = angular.module ('tipousuarioControllers',[]);
var moduleUsuario = angular.module ('usuarioControllers',[]);
var moduleProducto = angular.module ('productoControllers',[]);
var moduleFactura = angular.module ('facturaControllers',[]);
var moduleTipoproducto = angular.module('tipoproductoControllers',[]);
var moduleLinea = angular.module('lineaControllers',[]);

