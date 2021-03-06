app.controller('receitaController', ['$scope', '$rootScope', '$location', '$http', '$mdDialog', '$mdToast', 'ReceitaFactory', function($scope, $rootScope, $location, $http, $mdDialog, $mdToast, ReceitaFactory) {

    $scope.isLoading = true;

    $scope.receitas = [];
    $scope.receitas = ReceitaFactory.listar(
        {},
        function success() {
            $scope.isLoading = false;
        },
        function err() {
            console.log('Erro ao buscar receitas!');
        });

    $scope.editarReceita = function (receitaSKU) {
        $location.path('/receitas/editar/' + receitaSKU);
    };

    $scope.novaReceita = function () {
        $location.path('/receitas/nova');
    };

}]);

app.controller('formReceitaController', ['$scope', '$routeParams', '$location', '$http', '$mdDialog', '$mdToast', '$document', 'ReceitaFactory', 'MateriaPrimaFactory', function($scope, $routeParams, $location, $http, $mdDialog, $mdToast, $document, ReceitaFactory, MateriaPrimaFactory) {

    $scope.showToast = function(message) {
        $mdToast.show(
            $mdToast.simple()
                .textContent(message)
                .hideDelay(3000)
                // .parent($document[0].querySelector('md-card'))
                .position('top right left')
                .theme("success-toast")
        );
    };

    $scope.buttonAction = 'Cadastrar';
    $scope.receita = {};
    $scope.receita.receita = [];
    $scope.receita.receita[0] = {};

    $scope.novoItemReceita = function () {
        $scope.receita.receita.push({});
    };

    $scope.removerItemReceita = function (index) {
        $scope.receita.receita.splice(index, 1);
    };

    $scope.edicao = false;

    $scope.isLoadingMateriasPrimas = true;
    $scope.materiasPrimas = [];
    $scope.materiasPrimas = MateriaPrimaFactory.listar(
        {},
        function success() {
            console.log('Sucesso ao buscar matérias-primas!');
            $scope.isLoadingMateriasPrimas = false;
        },
        function err() {
            console.log('Erro ao buscar matérias-primas!');
        });

    if($routeParams.sku) {
        $scope.isLoading = true;
        $scope.receita = ReceitaFactory.receita(
            {sku: $routeParams.sku},
            function success() {

                $scope.edicao = true;
                $scope.buttonAction = 'Salvar';
                $scope.isLoading = false;

            },

            function err() {
                console.log('Erro ao buscar receita!');
            });
    }

    $scope.salvarReceita = function () {

        if($scope.edicao) {
            ReceitaFactory.editar(
                $scope.receita,
                function success() {
                    $scope.showToast('Receita editado com sucesso!');
                    $location.path('/receitas');
                },
                function err() {
                    console.log('Erro ao editar receita!');
                });
        } else {
            ReceitaFactory.novo(
                $scope.receita,
                function success() {
                    $scope.showToast('Receita cadastrado com sucesso!');
                    $location.path('/receitas');
                },
                function err() {
                    console.log('Erro ao cadastrar receita!');
                });
        }
    }

    $scope.unidades = [
        {
            sigla:"Kg",
            nome:"Quilo"
        },
        {
            sigla:"m",
            nome:"Metro"
        },
        {
            sigla:"Un",
            nome:"Unidade"
        }
    ];

}]);