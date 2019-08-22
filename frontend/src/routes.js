import Router from 'vue-router';

// 通用路由
import Empty from '@/views/404';
import Index from '@/views/index';
import About from '@/views/about';

// Auth相关路由
import Auth from '@/views/auth/auth';
import AuthLogin from '@/views/auth/children/login';
import AuthRegister from '@/views/auth/children/register';

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'index',
            component: Index,
        },
        {
            path: '/auth',
            name: 'auth',
            component: Auth,
            redirect: '/auth/login',
            children: [{
                path: 'login',
                component: AuthLogin
            }, {
                path: 'register',
                component: AuthRegister
            }]
        },
        {
            path: '/about',
            name: 'about',
            component: About,
        },
        {
            path: '*',
            name: '404',
            redirect: '/',
            component: Empty,
        },
    ],
});
