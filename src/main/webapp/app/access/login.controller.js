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
          // User or password invalid - we don't come back if the user logs in
          this.errorMessage = 'Invalid User Name or Password!';
        })
    }
  }
})();
