'use strict';

(() => {
  angular
    .module('flights.access')
    .controller('RegisterController', RegisterController);

  RegisterController.$inject = ['$scope', 'accessService', '$state', '$log'];

  function RegisterController($scope, accessService, $state, $log) {
    $log.debug('RegisterController-init');

    $scope.date = new Date();

    this.register = () => {
      $log.debug('RegisterController.register-init');
      this.errorMessage = null;
      accessService
        .register(this.user)
        .then(result => {
          // user already exists
          this.errorMessage = 'User already exists!';
        })
    }

    this.goToLogin = () => {
      $log.debug('RegisterController.goToLogin-init');
      $state.go('login');
    }
  }

})();
