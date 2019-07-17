import Router from 'vue-router';
import Auth from '@/views/auth';
import Index from '@/views/index';
import About from '@/views/about';
import Empty from '@/views/404';

export default new Router({
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
        },
        {
            path: '/about',
            name: 'about',
            component: About,
        },
        {
            path: '/404',
            name: '404',
            component: Empty,
        },
    ],
});
