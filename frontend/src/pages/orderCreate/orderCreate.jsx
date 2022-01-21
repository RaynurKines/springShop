import React, {useEffect, useState} from 'react';
import axios from "axios";

const OrderCreate = () => {

    const [products, setProducts] = useState([])
    const [getData, setGetData] = useState(false)

    const order = []

    Array.prototype.diff = function (a) {
        return this.filter(function (i) {
            return a.indexOf(i) < 0;
        });
    };

    const myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');

    useEffect(() => {
        axios
            .get("http://localhost:8080/products/show-all", {
                headers: {
                    "Access-Control-Allow-Origin": `*`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify,
                responseType: "json",
            })
            .then((response) => {
                setProducts(response.data)
                console.log(response.data)
            });
    }, [getData]);


    const addProduct = async (id) => {
        if (order.includes(id)) {
            for (let i = order.length - 1; i >= 0; i--) {
                if (order[i] === id) {
                    order.splice(i, 1);
                }
            }
            console.log('уже есть')
            console.log(order)

        } else {
            order.push(id)
            console.log(order)
        }
    }

    const createOrder = async () => {
        console.log('ok')
        const products_id = (order.map(String)).join(',')
        await fetch(`http://localhost:8080/purchases/create?customer_id=2&products_id=${products_id}`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},

        }).then((res) => {
            if (res.status === 400) {
                console.log('чёт не пошло')
            }
            if (res.status === 200) {
                console.log('всё ок')
                setGetData((prevState => !prevState))
            }
        }).catch((errors) => console.log(errors))
    }

    return (
        <div>
            <h1>Создание заказа</h1>
            <div className='table'>
                <div className="table-wrapper">
                    <table className="fl-table">
                        <thead>
                        <tr>
                            <th>Товар</th>
                            <th>Цена</th>
                            <th>Добавить в заказ</th>
                        </tr>
                        </thead>
                        <tbody>
                        {products
                            .map(item =>
                                <>
                                    <tr>
                                        <td>{item.name}</td>
                                        <td>{item.price}</td>
                                        <td>
                                            <input type="checkbox" id="order" name="order"
                                                   onChange={() => addProduct(item.id)}/>
                                            <label htmlFor="order">Добавить в заказ</label>
                                        </td>
                                    </tr>
                                </>
                            )}
                        </tbody>
                    </table>
                </div>
            </div>
            <button onClick={() => createOrder()}>Создать заказ</button>
        </div>
    );
};

export default OrderCreate;