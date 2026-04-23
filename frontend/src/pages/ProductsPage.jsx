import { useEffect, useState } from "react";
import api from "../services/api";
import ProductForm from "../components/ProductForm";
import ProductList from "../components/ProductList";

export default function ProductsPage() {
  const [products, setProducts] = useState([]);
  const [suppliers, setSuppliers] = useState([]);
  const [selectedProduct, setSelectedProduct] = useState(null);

  const fetchProducts = async () => {
    try {
      const response = await api.get("/products");
      setProducts(response.data);
    } catch (error) {
      console.error("Error fetching products:", error);
    }
  };

  const fetchSuppliers = async () => {
    try {
      const response = await api.get("/suppliers");
      setSuppliers(response.data);
    } catch (error) {
      console.error("Error fetching suppliers:", error);
    }
  };

  useEffect(() => {
    fetchProducts();
    fetchSuppliers();
  }, []);

  const handleEdit = (product) => {
    setSelectedProduct(product);
  };

  const handleDelete = async (id) => {
    try {
      await api.delete(`/products/${id}`);
      fetchProducts();

      if (selectedProduct && selectedProduct.id === id) {
        setSelectedProduct(null);
      }
    } catch (err) {
      console.error("Error deleting product:", err);
    }
  };

  const clearSelection = () => {
    setSelectedProduct(null);
  };

  return (
    <div className="page-container">
      <h1 className="page-title">Products</h1>

      <div className="supplier-layout">
        <div className="card">
          <h2>{selectedProduct ? "Edit Product" : "Add Product"}</h2>
          <ProductForm
            suppliers={suppliers}
            selectedProduct={selectedProduct}
            clearSelection={clearSelection}
            onProductSaved={fetchProducts}
          />
        </div>

        <div className="card">
          <h2>Product List</h2>
          <ProductList
            products={products}
            suppliers={suppliers}
            onEdit={handleEdit}
            onDelete={handleDelete}
          />
        </div>
      </div>
    </div>
  );
}