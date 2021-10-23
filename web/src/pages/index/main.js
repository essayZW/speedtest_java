import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import Index from './components/Index.vue';
import History from './components/History.vue';

Vue.config.productionTip = false;

Vue.use(ElementUI);
Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    component: Index 
  },
  {
    path: '/history',
    component: History
  }
];

const router = new VueRouter({
  routes: routes
});

new Vue({
  render: h => h(App),
  router: router
}).$mount('#app')
