(() => {
  angular
    .module('fastbook')
    .controller('AppController', AppController);

  AppController.$inject = ['accessService', 'chatService'];

  function AppController(accessService, chatService) {
    this.accessService = accessService;
    this.chatService = chatService;

  }

})();
