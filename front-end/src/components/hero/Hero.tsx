import styles from "./hero.module.scss";
import bm_hero from "../../assets/bookshelves.jpg";

type HeroProps = {
  children: React.ReactNode;
}

export const Hero = ({ children }: HeroProps) => {
  return (
    <div className={styles.hero}>
      {children}
      <img src={bm_hero} alt="bookmarket_hero_img" />
    </div>
  );
}