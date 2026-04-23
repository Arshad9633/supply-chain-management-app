import { Routes, Route, Link } from "react-router";
import SuppliersPage from "./pages/SuppliersPage";
import ProductsPage from "./pages/ProductsPage";
import InventoryPage from "./pages/InventoryPage";

export default function App() {
  return (
    <div>
      <nav>
        <div style={{ display: "flex", gap: "1rem" }}>
          <Link to="/">Suppliers</Link>
          <Link to="/products">Products</Link>
          <Link to ="/inventory">Inventory</Link>
        </div>
      </nav>

      <Routes>
        <Route path="/" element={<SuppliersPage />} />
        <Route path="/products" element={<ProductsPage />} />
        <Route path="/inventory" element={<InventoryPage />} />
      </Routes>
    </div>
  );
}