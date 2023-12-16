import { RouteRecordRaw } from 'vue-router';

const routes: RouteRecordRaw[] = [
  {
    path: '/auth', name: 'Auth',
    component: () => import('layouts/AuthLayout.vue'),
    children: [
      //{ path: '/', redirect: '/login' },
      { path: 'login', name: 'Login', component: () => import('pages/LoginPage.vue') },
      { path: 'register', name: 'Register', component: () => import('pages/RegisterPage.vue') },
    ],
    meta: { requiresAuth: false },
  },
  {
    path: '',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: 'beeforder', name: 'BeefOrder',
        component: () => import('pages/BeefOrderPage.vue')
      },
      {
        path: 'todos', name: 'Todo',
        component: () => import('pages/TodoPage.vue')
      },
      //{ path: 'home', component: () => import('pages/IndexPage.vue') },
      { path: 'posts', name: 'Posts', component: () => import('pages/PostsPage.vue') },
      { path: 'help', name: 'Help', component: () => import('pages/HelpPage.vue') },
      {
        path: 'profile', name: 'Profile',
        component: () => import('pages/ProfilePage.vue')
      },
    ],
    meta: { requiresAuth: true },
  },


  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue'),
    meta: { requiresAuth: false },
  },
];

export default routes;
