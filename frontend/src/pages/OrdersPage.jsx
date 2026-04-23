import { useEffect, useState } from "react";
import api from "../services/api";
import OrderForm from "../components/OrderForm";
import OrderList from "../components/OrderList";

export default function OrdersPage() {
  const [orders, setOrders] = useState([]);
  const [products, setProducts] = useState([]);

  const fetchOrders = async () => {
    try {
      const res = await api.get("/orders");
      setOrders(res.data);
    } catch (err) {
      console.error("Error fetching orders:", err);
    }
  };

  const fetchProducts = async () => {
    try {
      const res = await api.get("/products");
      setProducts(res.data);
    } catch (err) {
      console.error("Error fetching products:", err);
    }
  };

  useEffect(() => {
    fetchOrders();
    fetchProducts();
  }, []);

  return (
    <div className="page-container">
      <h1 className="page-title">Orders</h1>

      <div className="supplier-layout">
        <div className="card">
          <h2>Create Order</h2>
          <OrderForm products={products} onOrderSaved={fetchOrders} />
        </div>

        <div className="card">
          <h2>Order List</h2>
          <OrderList
            orders={orders}
            products={products}
            onOrderUpdated={fetchOrders}
            onOrderDeleted={fetchOrders}
          />
        </div>
      </div>
    </div>
  );
}