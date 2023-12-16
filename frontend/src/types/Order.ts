export default interface Order {
    orderNumber: number;
    name: string;
    mobile: string;
    done: boolean;
    orderDate: Date;
    actions?: any; //optional
    serialNo?: number //optional
}