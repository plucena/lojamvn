angular.module('App.services', []).factory('MateriaPrima', function($resource) {
	return $resource('http://loja.mybluemix.net/api/materiaprima/:sku', {
		sku : '@sku'}, // Precisa ser igual a variável de onde veio.
		{ 
			update : {
				method : 'PUT'
			}
		});
});