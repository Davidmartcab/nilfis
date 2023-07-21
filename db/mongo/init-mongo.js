db.createUser({
        user: 'root',
        pwd: 'toor',
        roles: [
            {
                role: 'readWrite',
                db: 'testDB',
            },
        ],
    });
db.createCollection('app_users', { capped: false });

db.app_users.insert([
    {
        "username": "ragnar777",
        "email": "ragnar777@gmail.com",
        "dni": "12345678A",
        "enabled": true,
        "password": "s3cr3t",
        "role":
        {
            "granted_authorities": ["ROLE_USER"]
        }
    },
    {
        "username": "heisenberg",
        "email": "heisenberg@gmail.com",
        "dni": "98765432B",
        "enabled": true,
        "password": "p4sw0rd",
        "role":
        {
            "granted_authorities": ["ROLE_USER"]
        }
    },
    {
        "username": "misterX",
        "email": "misterX@gmail.com",
        "dni": "45678901C",
        "enabled": true,
        "password": "misterX123",
        "role":
        {
            "granted_authorities": ["ROLE_USER", "ROLE_ADMIN"]
        }
    },
    {
        "username": "neverMore",
        "email": "neverMore@gmail.com",
        "dni": "87654321D",
        "enabled": true,
        "password": "4dmIn",
        "role":
        {
            "granted_authorities": ["ROLE_ADMIN"]
        }
    }
]);