import { useEffect, useState } from "react";
import api from "../services/api";
import SupplierForm from "../components/SupplierForm";
import SupplierList from "../components/SupplierList";

export default function SuppliersPage() {
  const [suppliers, setSuppliers] = useState([]);

  const fetchSuppliers = async () => {
    try {
      const response = await api.get("/suppliers");
      setSuppliers(response.data);
    } catch (error) {
      console.error("Error fetching suppliers:", error);
    }
  };

  useEffect(() => {
    fetchSuppliers();
  }, []);

  return (
    <div className="page-container">
      <h1 className="page-title">Suppliers</h1>

      <div className="supplier-layout">
        <div className="card">
          <h2>Add Supplier</h2>
          <SupplierForm onSupplierAdded={fetchSuppliers} />
        </div>

        <div className="card">
          <h2>Supplier List</h2>
          <SupplierList suppliers={suppliers} />
        </div>
      </div>
    </div>
  );
}