create keyspace todo_service
    with replication = {
        'class': 'SimpleStrategy',
        'replication_factor': 1
        };

use todo_service;

CREATE TABLE todo
(
    id      timeuuid PRIMARY KEY,
    text    text,
    is_done boolean
);