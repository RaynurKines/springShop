import './App.css';
import Products from "./pages/products/products";
import {Routes, Route, Link} from "react-router-dom";
import Customers from "./pages/customers/customers";
import Order from "./pages/order/order";
import OrderCreate from "./pages/orderCreate/orderCreate";
import Reports from "./pages/reports/reports";

function App() {
  return (
    <div className="App">
        <div className='navbar'>
            <ul>
                <li>
                    <Link style={{ textDecoration: 'none' }} to="/products">Продукты</Link>
                </li>
                <li>
                    <Link style={{ textDecoration: 'none' }} to="/customers">Покупатели</Link>
                </li>
                <li>
                    <Link style={{ textDecoration: 'none' }} to="/order">Заказы</Link>
                </li>
                <li>
                    <Link style={{ textDecoration: 'none' }} to="/order-create">Создание заказа</Link>
                </li>
                <li>
                    <Link style={{ textDecoration: 'none' }} to="/reports">Отчёты</Link>
                </li>
            </ul>
        </div>



        <Routes>
            <Route path='/products' element={<Products/>}/>
            <Route path='/customers' element={<Customers/>}/>
            <Route path='/order' element={<Order/>}/>
            <Route path='/order-create' element={<OrderCreate/>}/>
            <Route path='/reports' element={<Reports/>}/>
        </Routes>
    </div>
  );
}

export default App;
