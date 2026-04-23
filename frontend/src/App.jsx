import { Routes, Route, Link } from "react-router";
import SuppliersPage from "./pages/SuppliersPage";
import ProductsPage from "./pages/ProductsPage";
import InventoryPage from "./pages/InventoryPage";
import OrdersPage from "./pages/OrdersPage";

export default function App() {
  return (
    <div>
      <nav>
        <div style={{ display: "flex", gap: "1rem" }}>
          <Link to="/">Suppliers</Link>
          <Link to="/products">Products</Link>
          <Link to ="/inventory">Inventory</Link>
          <Link to="/orders">Orders</Link>
        </div>
      </nav>

      <Routes>
        <Route path="/" element={<SuppliersPage />} />
        <Route path="/products" element={<ProductsPage />} />
        <Route path="/inventory" element={<InventoryPage />} />
        <Route path="/orders" element={<OrdersPage />} />
      </Routes>
    </div>
  );
}