import { useUserStore } from 'stores/userstore';
import type { NavigationGuard } from 'vue-router';

export const authGuard: NavigationGuard = async (to, from, next) => {
    const ustore = useUserStore();

    console.log('IN AUTH GUARD:');
    console.log(ustore.id);

    // Check if the user is authenticated
    if (ustore.id != null) {
        next();
    } else {
        // You can use try/catch to get an id token and set it to your request header
        // ex: try { ... next() } catch { ... next({ name: '/login') }
        next('/auth/login');
    }
};