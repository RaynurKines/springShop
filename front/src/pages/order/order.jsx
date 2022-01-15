import React, {useEffect, useState} from 'react';
import axios from "axios";

const Order = () => {

    const [orders, setOrders] = useState([])
    const [getData, setGetData] = useState(false)

    const myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');

    useEffect(() => {
        axios
            .get("http://localhost:8080/purchases/show-all", {
                headers: {
                    "Access-Control-Allow-Origin": `*`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify,
                responseType: "json",
            })
            .then((response) => {
                setOrders(response.data)
                console.log(response.data)
            });
    }, [getData]);

    const deletePost = async (id) => {
        console.log(id)
        await fetch(`http://localhost:8080/purchases/delete/${id}`, {
            method: 'delete',
            headers: myHeaders,
            body: JSON.stringify(id)
        }).then((res) => {
            if (res.status === 200) {
                console.log('ok')
                setGetData((prevState => !prevState))
            }
            if (res.status === 403) {
                console.log('not Ok')
            }
            if(res.status ===500){
                alert('товар уже был продан')
            }
        }).catch((errors) => console.error(errors))
    }

    return (
        <div>
            <h1>
                Заказы
            </h1>
            <div className='table'>
                <div className="table-wrapper">
                    <table className="fl-table">
                        <thead>
                        <tr>
                            <th>id заказа</th>
                            <th>id покупателя</th>
                            <th>Продукты</th>
                            <th>Цена</th>
                            <th>Удалить</th>
                        </tr>
                        </thead>
                        <tbody>
                        {orders.map(order =>
                            <>
                                <tr>
                                    <td>{order.id}</td>
                                    <td>{order.customer}</td>
                                    <td>
                                        {order.products.map(item=>
                                            <tr>
                                                <td>{item.name}</td>
                                            </tr>
                                        )}
                                    </td>
                                    <td>{order.price}</td>
                                    <button className='btn' onClick={() => deletePost(order.id)}>
                                        удалить позицию
                                    </button>
                                </tr>
                            </>
                        )}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
};

export default Order;