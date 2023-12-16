import { axios } from 'boot/axios';

class OrderService {

    getAll(): Promise<any> {

        return axios.get('/api/orders');
    }


}

export default new OrderService();