
// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import 'whatwg-fetch'
import Vue from 'vue';
import App from './App';
import Vuex from 'vuex';
import routes from './routes';
import Router from 'vue-router';
import store from './vuex/store';

import ElementUI from 'element-ui';

Vue.use(Router);
Vue.use(ElementUI);

Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
    el: '#app',
    store: store,
    router: routes,
    components: { App },
    template: '<App/>',
});
