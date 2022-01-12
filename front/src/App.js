import './App.css';
import Products from "./pages/products/products";
import {Routes, Route, Link} from "react-router-dom";
import Customers from "./pages/customers/customers";
import Order from "./pages/order/order";

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
                    <Link style={{ textDecoration: 'none' }} to="/order">Заказ</Link>
                </li>
            </ul>
        </div>



        <Routes>
            <Route path='/products' element={<Products/>}/>
            <Route path='/customers' element={<Customers/>}/>
            <Route path='/order' element={<Order/>}/>
        </Routes>
    </div>
  );
}

export default App;
