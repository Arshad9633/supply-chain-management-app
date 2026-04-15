import { Routes, Route, Link } from "react-router";
import SuppliersPage from "./pages/SuppliersPage";

export default function App() {
  return (
    <div>
      <nav>
        <Link to="/">Supply Chain Dashboard</Link>
      </nav>

      <Routes>
        <Route path="/" element={<SuppliersPage />} />
      </Routes>
    </div>
  );
}