import * as z from "zod";

export const schema = z.object({
  email: z.email({message: "Please enter a valid email"}),
  password: z.string().min(1, { message: "Please enter your password" }),
});

export type LoginData = z.infer<typeof schema>;