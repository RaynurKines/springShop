import React, {useEffect, useState} from 'react';
import axios from "axios";
import Modal from "../components/Modal/Modal";
import "../components/Modal/modal.css"
import './style.css'

const Products = () => {

    const [products, setProducts] = useState([])
    const [getData, setGetData] = useState(false)

    const [modalActive, setModalActive] = useState(false)

    const [nameProduct, setNameProduct] = useState('')
    const [price, setPrice] = useState('')


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

    const deletePost = async (id) => {
        await fetch(`http://localhost:8080/products/delete/${id}`, {
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
            if (res.status === 500) {
                alert('товар уже был продан')
            }
        }).catch((errors) => console.error(errors))
    }

    const addProduct = async (e) => {
        e.preventDefault();
        await fetch(`http://localhost:8080/products/create?name=${nameProduct}&price=${price}`, {
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
            <h1>Продукты</h1>
            <div className='table'>
                <div className="table-wrapper">
                    <table className="fl-table">
                        <thead>
                        <tr>
                            <th>Товар</th>
                            <th>Цена</th>
                            <th>Удалить</th>
                        </tr>
                        </thead>
                        <tbody>
                        {products.map(item =>
                            <>
                                <tr>
                                    <td>{item.name}</td>
                                    <td>{item.price}</td>
                                    <td>
                                        <button className='btn' onClick={() => deletePost(item.id)}>
                                            удалить позицию
                                        </button>
                                    </td>
                                </tr>
                            </>
                        )}
                        </tbody>
                    </table>
                </div>
            </div>

            <button onClick={() => setModalActive(true)}>
                Добавить позицию
            </button>

            <Modal active={modalActive} setActive={setModalActive}>
                <form action="" onSubmit={addProduct}>
                    <div>
                        имя товара <input type="text" onChange={event => setNameProduct(event.target.value)}
                                          value={nameProduct}/>
                    </div>
                    <div>
                        цена товара <input type="text" onChange={event => setPrice(event.target.value)} value={price}/>
                    </div>
                    <input type="submit" value="создать товар" className="type-1" onClick={() => {
                        setModalActive(false)
                    }}/>
                </form>
            </Modal>
        </div>
    );
};

export default Products;