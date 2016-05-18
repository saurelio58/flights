'use strict';

(() => {
  angular
    .module('flights.access')
    .controller('LoginController', LoginController);

  LoginController.$inject = ['accessService', '$state', '$window', '$log'];

  function LoginController(accessService, $state, $window, $log) {
    $log.debug('LoginController-init')

    this.credentials;

    this.login = () => {
      $log.debug('LoginController.login-init')
      accessService
        .login(this.credentials)
        .then(result => {
          this.errorMessage = 'Invalid User Name or Password!';
        })
    }
  }
})();
