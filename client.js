const {PrismaClient} = require('@prisma/client');
const prisma = new PrismaClient();

export default async function SessionRegister(req, res) {

	   await prisma.user.update({
            where: {
                id: userId
            },
            data: {
                ClassRoom: {
                    create: [
                        {name: "", Grade: "", SubjectName: ""},
                    ],
                }
            }
        })
}