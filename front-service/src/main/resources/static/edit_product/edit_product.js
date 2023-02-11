angular.module('market').controller('updatedProductController',function ($scope,$http,$routeParams, $location){
    const contextPath = 'http://localhost:8189/core/';

    $scope.prepareProductForUpdate = function () {
        $http.get(contextPath + 'api/v1/products/' + $routeParams.productId)
            .then(function successCallback(response) {
                    $scope.updated_product = response.data;
                }, function failCallback(response) {
                    alert(response.data.messages);
                    $location.path('/store');
                }
            );
    }

    $scope.updateProduct = function () {
        $http.put(contextPath + 'api/v1/products', $scope.updated_product)
            .then(function successCallback(response) {
                    $scope.updated_product = null;
                    alert("Продукт успешно обновлен");
                    $location.path('/store');
                }, function failCallback(response) {
                    alert(response.data.messages);
                }
            );
    }

    $scope.prepareProductForUpdate();

});