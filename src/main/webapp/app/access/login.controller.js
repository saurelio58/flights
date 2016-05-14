'use strict';

(() => {
  angular
    .module('fastbook.access')
    .controller('LoginController', LoginController);

  LoginController.$inject = ['accessService', '$state', '$window', '$log'];

  function LoginController(accessService, $state, $window, $log) {
    this.credentials;

    $log.debug('Creating LoginController')
    this.credentials;
    this.login = () => {
      $log.debug('Calling LoginController.login()')
      accessService
        .login(this.credentials)
        .then(result => {
          // User or password invalid - we don't come back if the user logs in
          this.errorMessage = 'Invalid email or password!';
        })
    }
  }
})();
