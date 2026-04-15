import { useState } from "react";
import api from "../services/api";

export default function SupplierForm({ onSupplierAdded }) {
  const [formData, setFormData] = useState({
    supplierName: "",
    country: "",
    leadTimeDays: "",
    rating: "",
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await api.post("/suppliers", {
        supplierName: formData.supplierName,
        country: formData.country,
        leadTimeDays: Number(formData.leadTimeDays),
        rating: Number(formData.rating),
      });

      setFormData({
        supplierName: "",
        country: "",
        leadTimeDays: "",
        rating: "",
      });

      onSupplierAdded();
    } catch (error) {
      console.error("Error adding supplier:", error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="form-group">
        <label>Supplier Name</label>
        <input
          type="text"
          name="supplierName"
          value={formData.supplierName}
          onChange={handleChange}
          placeholder="Enter supplier name"
        />
      </div>

      <div className="form-group">
        <label>Country</label>
        <input
          type="text"
          name="country"
          value={formData.country}
          onChange={handleChange}
          placeholder="Enter country"
        />
      </div>

      <div className="form-group">
        <label>Lead Time (Days)</label>
        <input
          type="number"
          name="leadTimeDays"
          value={formData.leadTimeDays}
          onChange={handleChange}
          placeholder="Enter lead time"
        />
      </div>

      <div className="form-group">
        <label>Rating</label>
        <input
          type="number"
          step="0.1"
          name="rating"
          value={formData.rating}
          onChange={handleChange}
          placeholder="Enter rating"
        />
      </div>

      <button type="submit">Add Supplier</button>
    </form>
  );
}