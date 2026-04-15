export default function SupplierList({ suppliers, onEdit, onDelete }) {
  if (suppliers.length === 0) {
    return <p className="empty-state">No suppliers found.</p>;
  }

  return (
    <div className="supplier-list">
      {suppliers.map((supplier) => (
        <div key={supplier.id} className="supplier-item">
          <div className="supplier-name">{supplier.supplierName}</div>

          <div className="supplier-meta">
            <div><strong>Country:</strong> {supplier.country}</div>
            <div><strong>Lead Time:</strong> {supplier.leadTimeDays} days</div>
            <div><strong>Rating:</strong> {supplier.rating}</div>
          </div>

          <div className="action-buttons">
            <button onClick={() => onEdit(supplier)}>Edit</button>
            <button
              className="delete-btn"
              onClick={() => onDelete(supplier.id)}
            >
              Delete
            </button>
          </div>
        </div>
      ))}
    </div>
  );
}