export interface Package {
  id: number;
  name: string;
  price: number;
  sections: Section[];
}

export interface Section {
  id: number;
  name: string;
  options: string[];
}
